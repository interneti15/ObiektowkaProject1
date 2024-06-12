import java.awt.*;
public class Arrow extends Projectile{
    Arrow(Coordinates spawnCoordinates, Coordinates destination, Color team){
        super(spawnCoordinates);
        this.types.add(SimulationObjectType.ARROW);

        this.destination = destination;
        this.coordinates = spawnCoordinates;

        this.range = 10;
        this.damage = 10;

        this.maxStepDistance = 5;
        this.team = team;

        this.sprite = new Coordinates(10,10);
    }

    @Override
    public Arrow copy() {
        Arrow copiedArrow = (Arrow) super.copy(); // Call superclass copy() method
        // Copy additional fields specific to Arrow
        copiedArrow.destination = this.destination;
        copiedArrow.coordinates = new Coordinates(this.coordinates.x, this.coordinates.y);

        copiedArrow.range = this.range;
        copiedArrow.damage = this.damage;

        copiedArrow.maxStepDistance = this.maxStepDistance;
        copiedArrow.team = this.team;

        copiedArrow.sprite = new Coordinates(this.sprite.x  ,this.sprite.y);
        // Copy other fields as needed
        return copiedArrow;
    }
}
