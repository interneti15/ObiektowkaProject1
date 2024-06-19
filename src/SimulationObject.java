import java.io.Serializable;
import java.util.ArrayList;

enum SimulationObjectType{//Used for object identification
    SIMULATION_OBJECT,
    UNIT,
    UNIT_MELEE,
    UNIT_RANGE,
    PROJECTILE,
    ARCHER,
    ARROW,
    KNIGHT,
    SKELETON,
    WITCH
}
abstract class SimulationObject implements Serializable,Cloneable{
    protected Coordinates coordinates;
    protected Coordinates declaredNextCoordinates;// This will be used to give every unit the same opportunity to move
    protected int ID;
    private static int objectCount = 0;
    protected ArrayList<SimulationObjectType> types = new ArrayList<>();//This will be used to indentify methods and fields of class

    SimulationObject(){
        this.types.add(SimulationObjectType.SIMULATION_OBJECT);
        this.ID = objectCount;
        objectCount++;
    }
    protected Coordinates sprite;
    public abstract void walkTickDeclareNext();
    public abstract void walkTick();
    public abstract void attackTick();
    public abstract void afterTick();

    public boolean isThisType(SimulationObjectType desiredType){
        for(SimulationObjectType type : this.types){
            if (type == desiredType){
                return true;
            }
        }
        return false;
    }
    public double getSizeOfSprite(){
        return (this.sprite.x + this.sprite.y)/2;
    }

    // Hard copy method for SimulationObject
    public SimulationObject copy() {
        try {
            SimulationObject copiedObject = (SimulationObject) super.clone();
            copiedObject.coordinates = new Coordinates(this.coordinates.x, this.coordinates.y);
            copiedObject.declaredNextCoordinates = new Coordinates(this.declaredNextCoordinates.x, this.declaredNextCoordinates.y);
            copiedObject.sprite = new Coordinates(this.sprite.x, this.sprite.y);
            copiedObject.types = new ArrayList<>(this.types);
            return copiedObject;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
