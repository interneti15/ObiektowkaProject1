import java.awt.*;

public class Witch extends UnitRange{

    Witch(Coordinates coordinates, Color team){
        super(coordinates);
        this.types.add(SimulationObjectType.WITCH);

        this.coordinates = coordinates;
        this.health = 50;
        this.range = 1000;
        this.tickPerAttack = 0;
        this.maxStepDistance = 0.5;
        this.team = team;

        this.sprite = new Coordinates(60,60);
        this.summon = Skeleton.class;
        this.isSummoner = true;

    }
    @Override
    public Witch copy() {
        Witch copiedArcher = (Witch) super.copy();
        copiedArcher.health = this.health;
        copiedArcher.range = this.range;
        copiedArcher.tickPerAttack = this.tickPerAttack;
        copiedArcher.maxStepDistance = this.maxStepDistance;
        copiedArcher.sprite = new Coordinates(this.sprite.x, this.sprite.y);
        copiedArcher.projectile = this.projectile;
        copiedArcher.isSummoner = this.isSummoner;
        copiedArcher.summon = this.summon;
        return copiedArcher;
    }
}

