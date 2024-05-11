abstract class UnitRange extends Unit{

    UnitRange(){
        super();
        this.types.add(SimulationObjectType.UNITRANGE);
    }
    protected Projectile projectile;
    protected int damage;
}
