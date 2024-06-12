import java.io.*;
import java.util.ArrayList;

public class SouvenirHandler implements Serializable {

    public int tickNow;
    public ArrayList<SimulationObject> objectListSnapshot;


    static String fileName = "data.ser";
    SouvenirHandler(ArrayList<SimulationObject> objectListSnapshot, int tickNow){
        ArrayList<SimulationObject> tempList = new ArrayList<>();
        for (SimulationObject i : objectListSnapshot){
            tempList.add(i.copy());
        }

        this.objectListSnapshot = (ArrayList<SimulationObject>) tempList.clone();
        this.tickNow = tickNow;
    }
    static void saveToFile(ArrayList<SouvenirHandler> tickHistory){

        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tickHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadDataFromFile(SimulationEngine engine, int a){

        if (a <= 0){
            return;
        }
        ArrayList<SouvenirHandler> tickHistory = null;

        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            tickHistory = (ArrayList<SouvenirHandler>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(1);
        engine.loadSouvenirHelper(tickHistory.get(tickHistory.size() - a), tickHistory);

    }

    static void loadDataFromMemory(SimulationEngine engine, int a){
        if (a <= 0){
            return;
        }
        ArrayList<SouvenirHandler> copiedList = new ArrayList<>();
        for(int i = 0; i < SimulationEngine.souvenirPattern.size() && i < SimulationEngine.souvenirPattern.size() - a; i++) {

            copiedList.add(SimulationEngine.souvenirPattern.get(i).copy());
        }
        engine.loadSouvenirHelper(SimulationEngine.souvenirPattern.get(SimulationEngine.souvenirPattern.size() - a).copy(), copiedList);
        //engine.loadSouvenirHelper(SimulationEngine.souvenirPattern.getFirst(), SimulationEngine.souvenirPattern);
    }

    public SouvenirHandler copy(){
        ArrayList<SimulationObject> tempList = new ArrayList<>();
        for (SimulationObject i : this.objectListSnapshot){
            tempList.add(i.copy());
        }

        SouvenirHandler copied = new SouvenirHandler((ArrayList<SimulationObject>) tempList.clone(),this.tickNow);
        return  copied;
    }

}
