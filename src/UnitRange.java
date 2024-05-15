abstract class UnitRange extends Unit{

    UnitRange(Coordinates coordinates){
        super(coordinates);
        this.types.add(SimulationObjectType.UNIT_RANGE);
    }

    protected Projectile projectile;
    protected int damage;

    @Override
    public void attackTick() {

    }
}
