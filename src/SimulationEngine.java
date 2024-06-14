import java.util.ArrayList;
public class SimulationEngine {

    private ArrayList<SimulationObject> objectsToTick = new ArrayList<SimulationObject>();
    public static ArrayList<SimplifiedSimulationObject> simpleSimulationObjectList = new ArrayList<SimplifiedSimulationObject>();
    private static int tickCount = 0;
    public static ArrayList<SimulationObject> objectsToAdd = new ArrayList<SimulationObject>();
    public static ArrayList<SouvenirHandler> souvenirPattern = new ArrayList<>();
    //The two static fields below will represent the boundaries of the simulation
    public final static Coordinates maxPositive = new Coordinates(950,500);
    public final static Coordinates maxNegative = new Coordinates(0,0);


    SimulationEngine(){

    }

    public void addNewSimulationObject(SimulationObject newObject){
        objectsToTick.add(newObject);
    }

    public void tick() {//1 game tick consist of updateing list with coords of objects, then we to walk tick designed for changing of coords, then attack tick for atacking and after tick for any other things
        this.refreshSimpleList(); // Refreshing this whole list with object coordinates and basic info, this will be later used to count damge dealt between units

        try {
            addToSouvenirPatternList();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }

        //SouvenirHandler.saveToFile(SimulationEngine.souvenirPattern);
//        if (SimulationEngine.getTickCount() > 20){
//            this.loadSouvenir(1);
//            refreshSimpleList();
//            return;
//        }

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
    //Do not use!!!
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
    public void refreshSimpleList(){
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
    private void addToSouvenirPatternList() throws CloneNotSupportedException {
        if(souvenirPattern.size() == 200){
            souvenirPattern.removeFirst();
            // lista maxuje sie na 200 potem usuwa ostatni zapis
            System.out.println("usuwam pierwszy element!");
        }

        ArrayList<SimulationObject> tempList = new ArrayList<>();
        for (SimulationObject i : this.objectsToTick){
            tempList.add(i.copy());
        }

        souvenirPattern.add(new SouvenirHandler((ArrayList<SimulationObject>) tempList.clone(), SimulationEngine.getTickCount()));

    }

    public void loadSouvenirHelper(SouvenirHandler loadedTick, ArrayList<SouvenirHandler> fullTickHistory){
        ArrayList<SimulationObject> tempList = new ArrayList<>();
        for (SimulationObject i : loadedTick.objectListSnapshot){
            tempList.add(i.copy());
        }
        this.objectsToTick.clear();
        this.objectsToTick = (ArrayList<SimulationObject>)tempList.clone();
        SimulationEngine.tickCount = loadedTick.tickNow;
        SimulationEngine.souvenirPattern.clear();
        SimulationEngine.souvenirPattern = fullTickHistory;
        this.refreshSimpleList();
    }

    //    public void randomPlacement(){
//        //this.tick();
//        ArrayList<SimulationObject> randomizedList = new ArrayList<>();
//        this.refreshSimpleList();
//
//        try {
//            this.addToSouvenirPatternList();
//        }
//        catch (CloneNotSupportedException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        for (SimulationObject obj : this.objectsToTick){
//            if (obj.isThisType(SimulationObjectType.ARROW)){
//                continue;
//            }
//            obj.coordinates.x = SimulationEngine.maxPositive.x * Math.random();
//            randomizedList.add(obj);
//            obj.coordinates.y = SimulationEngine.maxPositive.y * Math.random();
//        }
//    SimulationEngine.tickCount++;
//
//        this.refreshSimpleList();
//}
    public void randomPlacement() {
        ArrayList<SimulationObject> randomizedList = new ArrayList<>();
        for (SimulationObject obj : this.objectsToTick) {
            if (obj.isThisType(SimulationObjectType.ARROW)) {
                continue;
            }

            boolean positionFound = false;
            while (!positionFound) {
                double newX = SimulationEngine.maxPositive.x * Math.random();
                double newY = SimulationEngine.maxPositive.y * Math.random();

                Coordinates newCoords = new Coordinates(newX, newY);

                // overlap check
                boolean isValid = true;
                for (SimulationObject other : randomizedList) {
                    if (Coordinates.distanceBetweenTwo(newCoords, other.coordinates) < 100) { // 100 is 2 times unit radius, so they don't overlap
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    obj.coordinates.x = newX;
                    obj.coordinates.y = newY;
                    randomizedList.add(obj);
                    positionFound = true;
                }
            }
        }
        SimulationEngine.tickCount++;

        this.refreshSimpleList();
    }

}
