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
        try {
            this.lastAttack = SimulationEngine.getTickCount();
            Constructor<? extends Projectile> constructor = projectile.getDeclaredConstructor(
                    Coordinates.class, Coordinates.class, Color.class
            );
            SimulationObject obj = constructor.newInstance(new Coordinates(this.coordinates.x, this.coordinates.y), SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates(), this.team);
            SimulationEngine.objectsToAdd.add(obj);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            System.out.println(e.toString());
        }

    
    }
}
