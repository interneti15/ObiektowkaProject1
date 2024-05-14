public class Knight extends UnitMelee{

    Knight(Coordinates c1){
        super();
        this.types.add(SimulationObjectType.KNIGHT);

        this.health = 100;
        this.range = 1;
        this.damage = 10;
        this.tickPerAttack = 20;
        this.maxStepDistance = 1;
        this.team = "any";

    }

    public void test(){
        System.out.println(1);
    }
}
