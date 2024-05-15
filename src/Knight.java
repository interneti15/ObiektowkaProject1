public class Knight extends UnitMelee{

    Knight(Coordinates coordinates, String team){
        super(coordinates);
        this.types.add(SimulationObjectType.KNIGHT);

        this.coordinates = coordinates;
        this.health = 100;
        this.range = 1;
        this.damage = 10;
        this.tickPerAttack = 20;
        this.maxStepDistance = 1;
        this.team = team;

    }

    public void test(){
        System.out.println(1);
    }
}
