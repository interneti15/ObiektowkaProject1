import java.util.ArrayList;
public class SimulationEngine {

    private ArrayList<SimulationObject> ObjectsToTick = new ArrayList<SimulationObject>();
    public static ArrayList<SimplifiedSimulationObject> SimpleSimulationObjectList = new ArrayList<SimplifiedSimulationObject>();
    public static int tickCount = 0;


    //The two static fields below will represent the boundaries of the simulation
    public final static Coordinates maxPositive = new Coordinates(100,100);
    public final static Coordinates maxNegative = new Coordinates(-100,-100);

    SimulationEngine(){

    }

    void tick() {//1 game tick consist of updateing list with coords of objects, then we to walk tick designed for changing of coords, then attack tick for atacking and after tick for any other things
        SimpleSimulationObjectList = new ArrayList<SimplifiedSimulationObject>(); // Refreshing this whole list with object coordinates and basic info, this will be later used to count damge dealt between units
        for (SimulationObject object : ObjectsToTick) {
            SimpleSimulationObjectList.add(new SimplifiedSimulationObject(object));
        }

        for (SimulationObject object : ObjectsToTick) {
            object.walkTickDeclareNext();
        }
        for (SimulationObject object : ObjectsToTick) {
            object.walkTick();
        }
        for (SimulationObject object : ObjectsToTick) {
            object.attackTick();
        }
        for (SimulationObject object : ObjectsToTick) {
            object.afterTick();
        }

        tickCount++;
    }
}
