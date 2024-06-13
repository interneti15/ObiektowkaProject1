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

    private double health = 0;

    private int lastDamageTaken= -(Integer.MAX_VALUE)/2;
    private int lastAttack = -(Integer.MAX_VALUE)/2;


    private Coordinates sprite;

    SimplifiedSimulationObject(SimulationObject object){
        this.coordinates = object.coordinates;
        this.ID = object.ID;
        this.types = object.types;
        this.sprite = object.sprite;


        if (object.isThisType(SimulationObjectType.PROJECTILE)){
            this.team = Color.gray;
        }
        else if (object.isThisType(SimulationObjectType.UNIT)){
            this.team = ((Unit)object).team;
            this.lastAttack = ((Unit)object).lastAttack;
            this.lastDamageTaken = ((Unit)object).lastDamageTaken;
            this.health = ((Unit)object).health;
        }
        else{
            this.team = Color.gray;
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

    public Color getColor(){// This is used to paint unit based on when they have taken dmg or atacked if they didn't we just return their team color
        if (SimulationEngine.getTickCount() - this.lastAttack < 5){
            return Color.yellow;
        }
        else if (SimulationEngine.getTickCount() - this.lastDamageTaken < 6){
            return Color.red;
        }
        return getTeam();
    }

    public Ellipse2D.Double getShape(){
        return new Ellipse2D.Double(this.coordinates.x - this.sprite.x/2, this.coordinates.y - this.sprite.y/2, this.sprite.x, this.sprite.y);
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
    public double getHealth(){
        return this.health;
    }
}


