abstract class UnitRange extends Unit{

    UnitRange(){
        super();
        this.types.add(SimulationObjectType.UNIT_RANGE);
    }
    protected Projectile projectile;
    protected int damage;
}
