import java.awt.*;

public class Skeleton extends UnitMelee{

    Skeleton(Coordinates coordinates, Color team){
        super(coordinates);
        this.types.add(SimulationObjectType.SKELETON);

        this.coordinates = coordinates;
        this.health = 1;
        this.range = 21;
        this.damage = 10;
        this.tickPerAttack = 30;
        this.maxStepDistance = 4;
        this.team = team;

        this.sprite = new Coordinates(20,20);
    }

    @Override
    public Skeleton copy() {
        Skeleton copiedKnight = (Skeleton) super.copy();
        copiedKnight.health = this.health;
        copiedKnight.range = this.range;
        copiedKnight.tickPerAttack = this.tickPerAttack;
        copiedKnight.maxStepDistance = this.maxStepDistance;
        copiedKnight.sprite = new Coordinates(this.sprite.x, this.sprite.y);

        return copiedKnight;
    }

}
