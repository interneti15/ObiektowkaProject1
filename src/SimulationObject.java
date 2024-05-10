enum SimulationObjectType{
    UNIT,
    UNITMELEE,
    KNIGHT
}
abstract class SimulationObject {
    protected Coordinates cords;
    protected int ID;
    static int objectCount = 0;
    protected Boolean isUnit;

    protected SimulationObjectType type;

    SimulationObject(SimulationObjectType type){
        this.ID = objectCount;
        objectCount++;
        this.type = type;
    }
    //protected |--| look <- tu klasa z wygladem sprita
    public abstract void walkTick();
    public abstract void attackTick();
    public abstract void afterTick();

}
