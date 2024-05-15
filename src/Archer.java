public class Archer extends UnitRange{

    Archer(Coordinates coordinates, String team){
        super(coordinates);
        this.types.add(SimulationObjectType.ARCHER);

        this.coordinates = coordinates;
        this.health = 100;
        this.range = 10;
        this.tickPerAttack = 20;
        this.maxStepDistance = 1;
        this.team = team;
        this.projectile = new Arrow();
    }
}

