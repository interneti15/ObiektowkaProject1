import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        SimulationEngine engine = new SimulationEngine();


        Knight temp = new Knight(new Coordinates(100,0),"blue");
        temp.health = 2000;
        engine.addNewSimulationObject(temp);

        temp = new Knight(new Coordinates(-100,1),"red");
        engine.addNewSimulationObject(temp);
        temp = new Knight(new Coordinates(-100,0),"red");
        engine.addNewSimulationObject(temp);
        temp = new Knight(new Coordinates(-100,2),"red");
        engine.addNewSimulationObject(temp);


        while (true){
            engine.tick();

            ArrayList<SimulationObject> lista = engine.DEBUG_getObjectsList();
            for (SimulationObject obj: lista){
                System.out.printf("ID: %d X: %f, Y: %f, Health: %f \n", ((Unit)obj).ID, ((Unit)obj).coordinates.x, ((Unit)obj).coordinates.y, ((Unit)obj).health);
            }
            System.out.println(SimulationEngine.SimpleSimulationObjectList.size());
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

        }


    }
}