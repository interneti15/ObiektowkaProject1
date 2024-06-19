import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

abstract class UnitRange extends Unit{

    UnitRange(Coordinates coordinates){
        super(coordinates);
        this.types.add(SimulationObjectType.UNIT_RANGE);
    }

    protected int damage;
    protected Class<? extends Projectile> projectile;

    protected boolean isSummoner = false;
    protected Class<? extends UnitMelee> summon;

    @Override
    public void attackTick(){

        if (SimulationEngine.getTickCount() < this.lastAttack + this.tickPerAttack){
            return;
        }

        //Finding the closest enemy and returning if none were found
        int closestEnemyIndex = findClosestEnemyIndex();
        if (closestEnemyIndex == -1){
            return;
        }

        //If closest enemy is not in range we return
        if (!(isCloseEnoughForAttack(Coordinates.distanceBetweenTwo(SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates(), this.coordinates) , this.range))) {
            return;
        }

        //Spawning new Simulation Object Projectile
        if (!this.isSummoner) {
            try {
                this.lastAttack = SimulationEngine.getTickCount();
                Constructor<? extends Projectile> constructor = projectile.getDeclaredConstructor(
                        Coordinates.class, Coordinates.class, Color.class
                );
                SimulationObject obj = constructor.newInstance(this.coordinates.copy(), SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates().copy(), this.team);
                SimulationEngine.objectsToAdd.add(obj);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        }
        else{
            try {
                if (Coordinates.distanceBetweenTwo(this.coordinates, SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates()) < Coordinates.distanceFrom00(this.sprite) + 10){
                    return;
                }


                this.lastAttack = SimulationEngine.getTickCount();
                Constructor<? extends UnitMelee> constructor = summon.getDeclaredConstructor(
                        Coordinates.class, Color.class
                );

                Coordinates deltaCoordinates = new Coordinates(SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates().x - this.coordinates.x, SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates().y - this.coordinates.y);
                double vectorLenght = Math.sqrt((Math.pow(deltaCoordinates.x,2) + Math.pow(deltaCoordinates.y,2)));

                SimulationObject obj = constructor.newInstance(new Coordinates(0,0), this.team);
                //We want so move only as far as to be in range
                double desiredMovementStep = Coordinates.distanceFrom00(this.sprite)/2 + Coordinates.distanceFrom00(obj.sprite)/2 + this.maxStepDistance/2;

                //We will divide the deltaCordinatex.x and .y by this number to lower the step size
                double mathConst = vectorLenght / desiredMovementStep;
                deltaCoordinates.x /= mathConst;
                deltaCoordinates.y /= mathConst;

                obj.coordinates = new Coordinates(this.coordinates.x + deltaCoordinates.x,this.coordinates.y + deltaCoordinates.y);
                SimulationEngine.objectsToAdd.add(obj.copy());
                obj = constructor.newInstance(new Coordinates(this.coordinates.x + deltaCoordinates.x+1,this.coordinates.y + deltaCoordinates.y+1), this.team);
                SimulationEngine.objectsToAdd.add(obj.copy());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public void walkTickDeclareNext() {

        if (!canMove){
            return;
        }
        //First we search for closest enemy, to be exact to its position in SimplifiedList
        int closestEnemyIndex = findClosestEnemyIndex();

        //if none were found we return as we will only move when there are enemies
        if (closestEnemyIndex == -1){
            return;
        }

        // We will treat the var below as a vector describing relation between this unit and the enemy
        Coordinates deltaCoordinates = new Coordinates(SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates().x - this.coordinates.x, SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates().y - this.coordinates.y);
        double vectorLenght = Math.sqrt((Math.pow(deltaCoordinates.x,2) + Math.pow(deltaCoordinates.y,2)));

        double desiredMovementStep;
        if (vectorLenght < this.range / 2){
            deltaCoordinates.x *= -1;
            deltaCoordinates.y *= -1;

            desiredMovementStep = Coordinates.distanceFrom00(deltaCoordinates);
        }
        else if (vectorLenght < this.range) {// We don't move if we are already in range
            return;
        }
        else{
            //We want so move only as far as to be in range
            desiredMovementStep = vectorLenght - this.range;
        }

        // but we need to limit this to maxStepDistance
        if (desiredMovementStep > this.maxStepDistance){
            desiredMovementStep = this.maxStepDistance;
        }

        //We will divide the deltaCordinatex.x and .y by this number to lower the step size
        double mathConst = vectorLenght / desiredMovementStep;
        deltaCoordinates.x /= mathConst;
        deltaCoordinates.y /= mathConst;

        //Then we update the declaredNextCoordinates
        this.declaredNextCoordinates.x = this.coordinates.x + deltaCoordinates.x;
        this.declaredNextCoordinates.y = this.coordinates.y + deltaCoordinates.y;

        checkBoundries();
    }
    @Override
    public UnitRange copy() {

        UnitRange copiedUnit = (UnitRange) super.copy();
        copiedUnit.coordinates = new Coordinates(this.coordinates.x, this.coordinates.y);
        copiedUnit.health = this.health;
        copiedUnit.damage = this.damage;
        copiedUnit.projectile = this.projectile;
        copiedUnit.isSummoner = this.isSummoner;
        copiedUnit.summon = this.summon;
        // Copy other fields as needed
        return copiedUnit;

    }
}
