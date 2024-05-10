abstract class SimulationObject {
    protected Coordinates cords;
    protected int ID;
    static int objectCount = 0;
    SimulationObject(){
        this.ID = objectCount;
        objectCount++;
    }
    //protected |--| look <- tu klasa z wygladem sprita
    public abstract void walkTick();
    public abstract void attackTick();
    public abstract void afterTick();

}
