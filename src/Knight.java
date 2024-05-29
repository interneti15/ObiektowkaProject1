import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Knight extends UnitMelee{

    Knight(Coordinates coordinates, Color team){
        super(coordinates);
        this.types.add(SimulationObjectType.KNIGHT);

        this.coordinates = coordinates;
        this.health = 100;
        this.range = 51;
        this.damage = 10;
        this.tickPerAttack = 20;
        this.maxStepDistance = 3;
        this.team = team;

        this.sprite = new Coordinates(50,50);
    }

    public void test(){
        System.out.println(1);
    }
}
