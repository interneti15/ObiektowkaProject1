public class SimplifiedSimulationObject //Just ID and Coordinates, updated in engine tick
{
    private Coordinates coordinates;
    private int ID;

    SimplifiedSimulationObject(Coordinates coordinates, int id){
        this.coordinates = coordinates;
        this.ID = id;
    }

    public int getID(){
        return this.ID;
    }
    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    public void setID(int id){
        this.ID = id;
    }
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
}

