import java.awt.*;
public class Knight extends UnitMelee{

    Knight(Coordinates coordinates, Color team){
        super(coordinates);
        this.types.add(SimulationObjectType.KNIGHT);

        this.coordinates = coordinates;
        this.health = 100;
        this.range = 50;
        this.damage = 10;
        this.tickPerAttack = 20;
        this.maxStepDistance = 5;
        this.team = team;

    }

    public void test(){
        System.out.println(1);
    }
}
