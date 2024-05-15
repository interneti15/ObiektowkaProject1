abstract class UnitMelee extends Unit{
    protected int damage;
    UnitMelee(Coordinates coordinates){
        super(coordinates);
        this.types.add(SimulationObjectType.UNIT_MELEE);
    }
}
