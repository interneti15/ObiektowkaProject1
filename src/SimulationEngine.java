import java.util.ArrayList;
public class SimulationEngine {

    private ArrayList<SimulationObject> objectsToTick = new ArrayList<SimulationObject>();
    public static ArrayList<SimplifiedSimulationObject> simpleSimulationObjectList = new ArrayList<SimplifiedSimulationObject>();
    private static int tickCount = 0;
    public static ArrayList<SimulationObject> objectsToAdd = new ArrayList<SimulationObject>();
    //The two static fields below will represent the boundaries of the simulation
    public final static Coordinates maxPositive = new Coordinates(1000,1000);
    public final static Coordinates maxNegative = new Coordinates(-1000,-1000);

    SimulationEngine(){
        
    }

    public void addNewSimulationObject(SimulationObject newObject){
        objectsToTick.add(newObject);
    }

    void tick() {//1 game tick consist of updateing list with coords of objects, then we to walk tick designed for changing of coords, then attack tick for atacking and after tick for any other things
        refreshSimpleList(); // Refreshing this whole list with object coordinates and basic info, this will be later used to count damge dealt between units

        for (SimulationObject object : objectsToTick) {
            object.walkTickDeclareNext();
        }
        for (SimulationObject object : objectsToTick) {
            object.walkTick();
        }
        for (SimulationObject object : objectsToTick) {
            object.attackTick();
        }
        for (SimulationObject object : objectsToTick) {
            object.afterTick();
        }

        //Applying dmgTaken to health
        applyDamage();
        //Removing objects whose health has fallen below 0
        removeDeadObjects();
        //Add recently spawned objects
        addNewObjects();

        tickCount++;
    }

    //DEBUG methods will only be used for debugging
    public ArrayList<SimulationObject> DEBUG_getObjectsList(){
        return this.objectsToTick;
    }
    public void DEBUG_refreshSimpleList(){
        refreshSimpleList();
    }

    public static int getTickCount(){
        return tickCount;
    }

    private void applyDamage() {
        for (int i = 0; i < simpleSimulationObjectList.size(); i++){
            if (this.objectsToTick.get(i).isThisType(SimulationObjectType.UNIT)){
                if (((Unit)this.objectsToTick.get(i)).isImmortal){
                    continue;
                }
                ((Unit)this.objectsToTick.get(i)).health -= simpleSimulationObjectList.get(i).getDmgTaken();
            }
        }
    }
    private void removeDeadObjects() {
        for (int i = 0; i < this.objectsToTick.size(); i++){
            if (this.objectsToTick.get(i).isThisType(SimulationObjectType.UNIT)){
                if (((Unit)this.objectsToTick.get(i)).health <= 0){
                    this.objectsToTick.remove(i);
                    i--;
                }
            }
        }
    }
    private void refreshSimpleList(){
        simpleSimulationObjectList = new ArrayList<SimplifiedSimulationObject>();
        for (SimulationObject object : objectsToTick) {
            simpleSimulationObjectList.add(new SimplifiedSimulationObject(object));
        }
    }
    private void addNewObjects(){
        this.objectsToTick.addAll(objectsToAdd);
        objectsToAdd.clear();
    }


}
