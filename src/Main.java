import java.lang.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SimulationEngine engine = new SimulationEngine();

        engine.addNewSimulationObject(new Knight(new Coordinates(100,100), "blue"));
        engine.addNewSimulationObject(new Knight(new Coordinates(-100,-100), "red"));

        engine.DEBUG_refreshSimpleList();
        for (SimplifiedSimulationObject obj: SimulationEngine.SimpleSimulationObjectList){
            System.out.printf("ID: %d X: %f, Y: %f \n", obj.getID(), obj.getCoordinates().x, obj.getCoordinates().y);
        }

        engine.tick();

        engine.DEBUG_refreshSimpleList();
        for (SimplifiedSimulationObject obj: SimulationEngine.SimpleSimulationObjectList){
            System.out.printf("ID: %d X: %f, Y: %f \n", obj.getID(), obj.getCoordinates().x, obj.getCoordinates().y);
        }

    }
}