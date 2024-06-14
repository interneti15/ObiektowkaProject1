import java.awt.*;
import java.awt.geom.Ellipse2D;

abstract class Unit extends SimulationObject
{
    protected double health;//health
    protected double range;//range
    protected double maxStepDistance;//max distance able to move per tick
    protected double tickPerAttack;//ticks between atack opportuniteis
    protected Color team;//Team to which the unit belongs
    protected boolean canMove = true;
    protected boolean isImmortal = false;
    protected int lastAttack = -(Integer.MAX_VALUE)/2; // At which tick an attack occured
    protected int lastDamageTaken = -(Integer.MAX_VALUE)/2; // At which tick an unit has taken damage


    public Unit(Coordinates coordinates) {
        super();
        this.types.add(SimulationObjectType.UNIT);

        this.declaredNextCoordinates = coordinates;//This is important in case the object were to not move
    }
    //protected String type;//more like name of the unit  archer,knight

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

        if (vectorLenght < this.range) {// We don't move if we are already in range
            return;
        }

        //We want so move only as far as to be in range
        double desiredMovementStep = vectorLenght - this.range + 1;

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
    public void walkTick(){
        if (!canMove){
            return;
        }
        this.coordinates = this.declaredNextCoordinates;
    }

    @Override
    public void afterTick() {

    }

    protected int findClosestEnemyIndex(){
        double minimumDistanceFound = Double.MAX_VALUE;

        int closestEnemyIndex = -1; //-1 will mean that no object was found

        // We ignore objects that team is the same as this unit and system labaled objects, we only search for enemy units
        for (int i = 0; i < SimulationEngine.simpleSimulationObjectList.size(); i++){
            SimplifiedSimulationObject object = SimulationEngine.simpleSimulationObjectList.get(i);
            if (object.getTeam() == this.team || object.getTeam() == Color.gray || object.getID() == this.ID){
                continue;
            }
            double distanceInThisIteration = Coordinates.distanceBetweenTwo(this.coordinates, object.getCoordinates());
            if(distanceInThisIteration < minimumDistanceFound){
                minimumDistanceFound = distanceInThisIteration;
                closestEnemyIndex = i;
            }
        }

        return closestEnemyIndex;
    }

    protected void checkBoundries(){
        if (this.declaredNextCoordinates.x > SimulationEngine.maxPositive.x){
            this.declaredNextCoordinates.x = SimulationEngine.maxPositive.x;
        }
        else if (this.declaredNextCoordinates.x < SimulationEngine.maxNegative.x){
            this.declaredNextCoordinates.x = SimulationEngine.maxNegative.x;
        }

        if (this.declaredNextCoordinates.y > SimulationEngine.maxPositive.y){
            this.declaredNextCoordinates.y = SimulationEngine.maxPositive.y;
        }
        else if (this.declaredNextCoordinates.y < SimulationEngine.maxNegative.y){
            this.declaredNextCoordinates.y = SimulationEngine.maxNegative.y;
        }
    }

    static boolean isCloseEnoughForAttack(double distance, double range){//This will be used to check if isCloseEnoughForAttack
        return distance <= range + 0.01;
    }
    @Override
    public Unit copy() {

        Unit copiedUnit = (Unit) super.copy();
        copiedUnit.coordinates = new Coordinates(this.coordinates.x, this.coordinates.y);
        copiedUnit.health = this.health;
        copiedUnit.range = this.range;
        copiedUnit.maxStepDistance = this.maxStepDistance;
        copiedUnit.tickPerAttack = this.tickPerAttack;
        copiedUnit.team = this.team;
        copiedUnit.canMove = this.canMove;
        copiedUnit.isImmortal = this.isImmortal;
        copiedUnit.lastAttack = this.lastAttack;
        copiedUnit.lastDamageTaken = this.lastDamageTaken;
        // Copy other fields as needed
        return copiedUnit;

    }
}
