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
    }
}
