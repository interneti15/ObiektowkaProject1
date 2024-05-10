import java.util.ArrayList;
public class SimulationEngine {

    protected ArrayList<SimulationObject> ObjectsToTick;
    public static ArrayList<SimplifiedSimulationObject> ObjectCoordinatesList = new ArrayList<SimplifiedSimulationObject>();

    SimulationEngine(){
        ObjectsToTick = new ArrayList<SimulationObject>();
    }

    void tick() {
        ObjectCoordinatesList = new ArrayList<SimplifiedSimulationObject>(); // Refreshing this whole list with object coordinates
        for (SimulationObject object : ObjectsToTick) {
            ObjectCoordinatesList.add(new SimplifiedSimulationObject(object.cords, object.ID));
        }

        for (SimulationObject object : ObjectsToTick) {// Checking if unit has died will be after the tick
            object.doTick();
        }


    }
}
