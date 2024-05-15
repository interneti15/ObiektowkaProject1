import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        SimulationEngine engine = new SimulationEngine();


        if (false) {
            Knight temp = new Knight(new Coordinates(100, 0), "blue");
            temp.health = 2000;
            engine.addNewSimulationObject(temp);

            temp = new Knight(new Coordinates(-100, 1), "red");
            engine.addNewSimulationObject(temp);
            temp = new Knight(new Coordinates(-100, 0), "red");
            engine.addNewSimulationObject(temp);
            temp = new Knight(new Coordinates(-100, 2), "red");
            engine.addNewSimulationObject(temp);
        }

        if (false) {
            engine.addNewSimulationObject(new Arrow(new Coordinates(0, 0), new Coordinates(100, 100), "red"));
            engine.addNewSimulationObject(new Knight(new Coordinates(20, 20), "red"));
        }

        if (false){
            Knight knight = new Knight(new Coordinates(200, 200), "red");
            knight.canMove = false;
            knight.isImmortal = true;
            engine.addNewSimulationObject(knight);

            Archer archer = new Archer(new Coordinates(0,0), "green");
            archer.range = 10000;
            engine.addNewSimulationObject(archer);
        }

        while (true){
            engine.tick();

            ArrayList<SimulationObject> lista = engine.DEBUG_getObjectsList();
            for (SimulationObject obj: lista){

                System.out.printf("ID: %d Type: %s X: %f, Y: %f, Health: %f \n", ((Unit)obj).ID,obj.getClass().getName(), ((Unit)obj).coordinates.x, ((Unit)obj).coordinates.y, ((Unit)obj).health);
            }
            engine.DEBUG_refreshSimpleList();
            System.out.println(SimulationEngine.simpleSimulationObjectList.size());
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

        }


    }
}