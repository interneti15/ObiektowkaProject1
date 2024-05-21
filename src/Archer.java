import java.awt.*;
public class Archer extends UnitRange{

    Archer(Coordinates coordinates, Color team){
        super(coordinates);
        this.types.add(SimulationObjectType.ARCHER);

        this.coordinates = coordinates;
        this.health = 100;
        this.range = 200;
        this.tickPerAttack = 20;
        this.maxStepDistance = 1;
        this.team = team;

        this.projectile = Arrow.class;
    }
}

