import java.util.ArrayList;

public class SimplifiedSimulationObject //Just ID and Coordinates, updated in engine tick
{
    private Coordinates coordinates;
    private int ID;
    protected ArrayList<SimulationObjectType> types;
    private int dmgTaken = 0; // We will use this to count damage that given object has taken
    private String team;

    SimplifiedSimulationObject(Coordinates coordinates, int id, ArrayList<SimulationObjectType> types, String team){
        this.coordinates = coordinates;
        this.ID = id;
        this.types = types;
        this.team = team;
    }
    SimplifiedSimulationObject(Coordinates coordinates, int id, ArrayList<SimulationObjectType> types){
        this.coordinates = coordinates;
        this.ID = id;
        this.types = types;
        this.team = "system";
    }


    public int getID(){
        return this.ID;
    }
    public Coordinates getCoordinates(){
        return this.coordinates;
    }
    public ArrayList<SimulationObjectType> getTypes() {
        return this.types;
    }

    public boolean isThisType(SimulationObjectType desiredType){
        for(SimulationObjectType type : types){
            if (type == desiredType){
                return true;
            }
        }
        return false;
    }

    public int getDmgTaken() {
        return dmgTaken;
    }
    public void setID(int id){
        this.ID = id;
    }
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public void setDmgTaken(int dmgTaken) {
        this.dmgTaken = dmgTaken;
    }
}

