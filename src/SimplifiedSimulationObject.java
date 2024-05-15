import java.util.ArrayList;

public class SimplifiedSimulationObject //Simplified SimulationObject used for managing interactions counting dmg etc, updated in engine tick
{
    private Coordinates coordinates;
    private int ID;
    protected ArrayList<SimulationObjectType> types;
    private int dmgTaken = 0; // We will use this to count damage that given object has taken
    private String team;

    SimplifiedSimulationObject(SimulationObject object){
        this.coordinates = object.coordinates;
        this.ID = object.ID;
        this.types = object.types;

        if (object.isThisType(SimulationObjectType.UNIT)){
            if (object.isThisType(SimulationObjectType.PROJECTILE)){// We will label projectiles as system
                this.team = "system";
            }
            else{
                this.team = ((Unit)object).team;
            }
        }
        else{
            this.team = "system";
        }
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

    public String getTeam() {
        return team;
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

