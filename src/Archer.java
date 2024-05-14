public class Archer extends UnitRange{

    Archer(Coordinates c1){
        super();
        this.types.add(SimulationObjectType.ARCHER);

        this.health = 100;
        this.range = 10;
        this.tickPerAttack = 20;
        this.maxStepDistance = 1;
        this.team = "any";
        this.projectile = new Arrow();
    }
}

