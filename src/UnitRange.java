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

        //Finding the closest enemy and returning if none were found
        int closestEnemyIndex = findClosestEnemyIndex();
        if (closestEnemyIndex == -1){
            return;
        }

        //Spawning new Simulation Object Projectile
        try {
            Constructor<? extends Projectile> constructor = projectile.getDeclaredConstructor(
                    Coordinates.class, Coordinates.class, String.class
            );
            SimulationObject obj = constructor.newInstance(new Coordinates(this.coordinates.x, this.coordinates.y), SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates(), new String(this.team));
            SimulationEngine.objectsToAdd.add(obj);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            System.out.println(e.toString());
        }

    
    }
}
