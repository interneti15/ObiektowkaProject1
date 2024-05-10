import java.util.ArrayList;
public class SimulationEngine {

    protected ArrayList<SimulationObject> ObjectsToTick;
    public static ArrayList<SimplifiedSimulationObject> ObjectCoordinatesList = new ArrayList<SimplifiedSimulationObject>();

    SimulationEngine(){
        ObjectsToTick = new ArrayList<SimulationObject>();
    }

    void tick() {//1 game tick consist of updateing list with coords of objects, then we to walk tick designed for changing of coords, then attack tick for atacking and after tick for any other things
        ObjectCoordinatesList = new ArrayList<SimplifiedSimulationObject>(); // Refreshing this whole list with object coordinates
        for (SimulationObject object : ObjectsToTick) {
            ObjectCoordinatesList.add(new SimplifiedSimulationObject(object.cords, object.ID));
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


    }
}
