abstract class UnitMelee extends Unit{
    protected int damage;
    UnitMelee(){
        super();
        this.types.add(SimulationObjectType.UNIT_MELEE);
    }
}
