public class Knight extends UnitMelee{

    Knight(Coordinates c1){
        super();
        this.health = 100;
        this.range = 1;
        this.damage = 10;
        this.tickPerAttack = 20;
        this.walkSpeed = 1;
        this.team = "any";
        this.type = "knight";
    }
}