public class Archer extends UnitRange{

    Archer(Coordinates c1){
        super();
        this.health = 100;
        this.range = 10;
        this.tickPerAttack = 20;
        this.walkSpeed = 1;
        this.team = "any";
        this.type = "archer";
        this.projectile = new Arrow();
    }
}

