abstract class UnitMelee extends Unit{
    protected int damage;
    UnitMelee(Coordinates coordinates){
        super(coordinates);
        this.types.add(SimulationObjectType.UNIT_MELEE);
    }

    @Override
    public void attackTick() {

        int closestEnemyIndex = findClosestEnemyIndex();
        if (closestEnemyIndex == -1){
            return;
        }

        if (Coordinates.distanceBetweenTwo(SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates(), this.coordinates) <= this.range){

            int dmgTaken = SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getDmgTaken();
            SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).setDmgTaken(dmgTaken + this.damage);
        }

    }
}
