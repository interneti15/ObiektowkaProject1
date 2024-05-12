import java.util.ArrayList;

enum SimulationObjectType{//Used for object identification
    SIMULATION_OBJECT,
    UNIT,
    UNIT_MELEE,
    UNIT_RANGE,
    PROJECTILE,
    ARCHER,
    ARROW,
    KNIGHT
}
abstract class SimulationObject {
    protected Coordinates coordinates;
    protected int ID;
    private static int objectCount = 0;
    protected ArrayList<SimulationObjectType> types = new ArrayList<>();//This will be used to indentify methods and fields of class

    SimulationObject(){
        this.types.add(SimulationObjectType.SIMULATION_OBJECT);
        this.ID = objectCount;
        objectCount++;
    }
    //protected |--| look <- tu klasa z wygladem sprita
    public abstract void walkTick();
    public abstract void attackTick();
    public abstract void afterTick();

    public boolean isThisType(SimulationObjectType desiredType){
        for(SimulationObjectType type : types){
            if (type == desiredType){
                return true;
            }
        }
        return false;
    }

}
