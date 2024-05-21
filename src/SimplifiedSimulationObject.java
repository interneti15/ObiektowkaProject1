import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.*;

public class SimplifiedSimulationObject //Simplified SimulationObject used for managing interactions counting dmg etc, updated in engine tick
{
    private Coordinates coordinates;
    private int ID;
    protected ArrayList<SimulationObjectType> types;
    private int dmgTaken = 0; // We will use this to count damage that given object has taken
    private Color team;

    SimplifiedSimulationObject(SimulationObject object){
        this.coordinates = object.coordinates;
        this.ID = object.ID;
        this.types = object.types;

        if (object.isThisType(SimulationObjectType.UNIT)){
            if (object.isThisType(SimulationObjectType.PROJECTILE)){// We will label projectiles as green
                this.team = Color.green;
            }
            else{
                this.team = ((Unit)object).team;
            }
        }
        else{
            this.team = Color.green;
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

    public Color getTeam() {
        return team;
    }

    public Ellipse2D.Double getShape(){
        return new Ellipse2D.Double(this.coordinates.x, this.coordinates.y, 50, 50);
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


