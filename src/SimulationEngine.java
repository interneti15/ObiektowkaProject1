import java.util.ArrayList;
public class SimulationEngine {

    private ArrayList<SimulationObject> ObjectsToTick = new ArrayList<SimulationObject>();
    public static ArrayList<SimplifiedSimulationObject> SimpleSimulationObjectList = new ArrayList<SimplifiedSimulationObject>();
    public static int tickCount = 0;


    //The two static fields below will represent the boundaries of the simulation
    public final static Coordinates maxPositive = new Coordinates(1000,1000);
    public final static Coordinates maxNegative = new Coordinates(-1000,-1000);

    SimulationEngine(){

    }

    public void addNewSimulationObject(SimulationObject newObject){
        ObjectsToTick.add(newObject);
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

        //Applying dmgTaken to health
        for (int i = 0; i < SimpleSimulationObjectList.size(); i++){
            if (this.ObjectsToTick.get(i).isThisType(SimulationObjectType.UNIT)){
                ((Unit)this.ObjectsToTick.get(i)).health -= SimpleSimulationObjectList.get(i).getDmgTaken();
            }
        }
        //Removing objects whose health has fallen below 0
        for (int i = 0; i < this.ObjectsToTick.size(); i++){
            if (this.ObjectsToTick.get(i).isThisType(SimulationObjectType.UNIT)){
                if (((Unit)this.ObjectsToTick.get(i)).health < 0){
                    this.ObjectsToTick.remove(i);
                    i--;
                }
            }
        }

        tickCount++;
    }


    //DEBUG methods will only be used for debugging
    public void DEBUG_refreshSimpleList(){
        SimpleSimulationObjectList = new ArrayList<SimplifiedSimulationObject>();
        for (SimulationObject object : ObjectsToTick) {
            SimpleSimulationObjectList.add(new SimplifiedSimulationObject(object));
        }
    }

    public ArrayList<SimulationObject> DEBUG_getObjectsList(){
        return this.ObjectsToTick;
    }
}
