import java.awt.*;
public class Archer extends UnitRange{

    Archer(Coordinates coordinates, Color team){
        super(coordinates);
        this.types.add(SimulationObjectType.ARCHER);

        this.coordinates = coordinates;
        this.health = 100;
        this.range = 400;
        this.tickPerAttack = 40;
        this.maxStepDistance = 1;
        this.team = team;

        this.sprite = new Coordinates(50,50);

        this.projectile = Arrow.class;
    }
@Override
    public Archer copy() {
        Archer copiedArcher = (Archer) super.copy();
        copiedArcher.health = this.health;
        copiedArcher.range = this.range;
        copiedArcher.tickPerAttack = this.tickPerAttack;
        copiedArcher.maxStepDistance = this.maxStepDistance;
        copiedArcher.sprite = new Coordinates(this.sprite.x, this.sprite.y);
        copiedArcher.projectile = this.projectile;
        return copiedArcher;
    }
}

