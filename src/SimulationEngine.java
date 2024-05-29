import java.util.ArrayList;
public class SimulationEngine {

    private ArrayList<SimulationObject> objectsToTick = new ArrayList<SimulationObject>();
    public static ArrayList<SimplifiedSimulationObject> simpleSimulationObjectList = new ArrayList<SimplifiedSimulationObject>();
    private static int tickCount = 0;
    public static ArrayList<SimulationObject> objectsToAdd = new ArrayList<SimulationObject>();
    //The two static fields below will represent the boundaries of the simulation
    public final static Coordinates maxPositive = new Coordinates(950,950);
    public final static Coordinates maxNegative = new Coordinates(0,0);


    SimulationEngine(){
        
    }

    public void addNewSimulationObject(SimulationObject newObject){
        objectsToTick.add(newObject);
    }

    public void tick() {//1 game tick consist of updateing list with coords of objects, then we to walk tick designed for changing of coords, then attack tick for atacking and after tick for any other things
        refreshSimpleList(); // Refreshing this whole list with object coordinates and basic info, this will be later used to count damge dealt between units

        for (SimulationObject object : this.objectsToTick) {
            object.walkTickDeclareNext();
        }
        for (SimulationObject object : this.objectsToTick) {
            object.walkTick();
        }

        collisionCheckAndFix();
        for (SimulationObject object : this.objectsToTick) {// ColisionCheck method changes declaredPosition of objects, so we need to iterate once more over walkTick method
            object.walkTick();
        }

        for (SimulationObject object : this.objectsToTick) {
            object.attackTick();
        }
        for (SimulationObject object : this.objectsToTick) {
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

                if (simpleSimulationObjectList.get(i).getDmgTaken() > 0){
                    ((Unit)this.objectsToTick.get(i)).lastDamageTaken = SimulationEngine.getTickCount();
                }

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

    private void collisionCheckAndFix(){// This method will move objects, so they don't overlap each other, When objects are within collisionRange we calculate vectors betwen them reverse them and apply some smoothing
        double smoothnessConst = 32;//Sensitivity something??
        int collisionConstX = 1;
        int collisionConstY = 1;

        for (SimulationObject obj : this.objectsToTick){
            if (!obj.isThisType(SimulationObjectType.UNIT) || obj.isThisType(SimulationObjectType.PROJECTILE)){
                continue;
            }

            int counter = 0;
            Coordinates pushVector = new Coordinates();

            for (SimulationObject secondObj : this.objectsToTick){
                if (obj.ID == secondObj.ID || !secondObj.isThisType(SimulationObjectType.UNIT) || secondObj.isThisType(SimulationObjectType.PROJECTILE)){
                    continue;
                }

                double collisionRange = ((obj.getSizeOfSprite() + secondObj.getSizeOfSprite())/2) - 0.01;

                if (collisionRange <= 0){
                    continue;
                }

                if(Coordinates.distanceBetweenTwo(obj.coordinates, secondObj.coordinates) < collisionRange){
                    if (Coordinates.distanceBetweenTwo(obj.coordinates, secondObj.coordinates) == 0){
                        obj.coordinates.x += 0.1*collisionConstX;
                        obj.coordinates.y += 0.1*collisionConstY;

                        if (collisionConstX == 1){
                            collisionConstX = -1;
                        }
                        else{
                            collisionConstX = 1;

                            if (collisionConstY == 1){
                                collisionConstY = -1;
                            }
                            else{
                                collisionConstY = 1;
                            }
                        }

                    }
                    Coordinates temp = new Coordinates();
                    temp.x = -(secondObj.coordinates.x - obj.coordinates.x);
                    temp.y = -(secondObj.coordinates.y - obj.coordinates.y);

                    double lenght = Coordinates.distanceFrom00(temp);
                    double mathConst = collisionRange / lenght;

                    temp.x *= mathConst;
                    temp.y *= mathConst;

                    pushVector.x += temp.x/smoothnessConst;
                    pushVector.y += temp.y/smoothnessConst;

                    counter++;
                }
            }
            if (counter == 0){
                continue;
            }
            pushVector.x /= counter;
            pushVector.y /= counter;

            obj.declaredNextCoordinates.x += pushVector.x;
            obj.declaredNextCoordinates.y += pushVector.y;
        }
    }

}
