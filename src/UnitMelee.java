abstract class UnitMelee extends Unit{
    protected int damage;
    UnitMelee(Coordinates coordinates){
        super(coordinates);
        this.types.add(SimulationObjectType.UNIT_MELEE);
    }

    @Override
    public void attackTick() {

        if (SimulationEngine.getTickCount() < this.lastAttack + this.tickPerAttack){
            return;
        }

        int closestEnemyIndex = findClosestEnemyIndex();
        if (closestEnemyIndex == -1){
            return;
        }

        double tempRange = this.range + Coordinates.distanceFrom00(SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getSprite())/2;

        if (isCloseEnoughForAttack(Coordinates.distanceBetweenTwo(SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates(), this.coordinates) , tempRange)){

            int dmgTaken = SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getDmgTaken();
            SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).setDmgTaken(dmgTaken + this.damage);
            this.lastAttack = SimulationEngine.getTickCount();
        }

    }
    @Override
    public UnitMelee copy() {

        UnitMelee copiedUnit = (UnitMelee) super.copy();
        copiedUnit.coordinates = new Coordinates(this.coordinates.x, this.coordinates.y);
        copiedUnit.damage = this.damage;
        return copiedUnit;

    }
}
