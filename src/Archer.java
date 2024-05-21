import java.awt.*;
public class Archer extends UnitRange{

    Archer(Coordinates coordinates, Color team){
        super(coordinates);
        this.types.add(SimulationObjectType.ARCHER);

        this.coordinates = coordinates;
        this.health = 100;
        this.range = 400;
        this.tickPerAttack = 50;
        this.maxStepDistance = 1;
        this.team = team;

        this.sprite = new Coordinates(50,50);

        this.projectile = Arrow.class;
    }
}

