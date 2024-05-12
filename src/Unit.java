abstract class Unit extends SimulationObject
{
    protected double health;//health
    protected double range;//range
    protected double walkSpeed;//max distance able to move per tick
    protected double tickPerAttack;//ticks between atack opportuniteis
    protected String team;//Team to which the unit belongs


    public Unit() {
        super();
        this.types.add(SimulationObjectType.UNIT);
    }
    //protected String type;//more like name of the unit  archer,knight

    @Override
    public void walkTick() {

        //First we search for closest enemy, to be exact to its position in SimplifiedList
        int closestEnemyIndex = findClosestEnemyIndex();

        //if none were found we return as we will only move when there are enemies
        if (closestEnemyIndex == -1){
            return;
        }

    }
    @Override
    public void attackTick() {

    }
    @Override
    public void afterTick() {

    }


    protected int findClosestEnemyIndex(){
        double minimumDistanceFound = Double.MAX_VALUE;

        int closestEnemyIndex = -1; //-1 will mean that no object was found

        // We ignore objects that team is the same as this unit and system labaled objects, we only search for enemy units
        for (int i = 0; i < SimulationEngine.SimpleSimulationObjectList.size(); i++){
            SimplifiedSimulationObject object = SimulationEngine.SimpleSimulationObjectList.get(i);
            if (object.getTeam().equals(this.team) || object.getTeam().equals("system")){
                continue;
            }
            double distanceInThisIteration = Coordinates.distanceBetweenTwo(this.coordinates, object.getCoordinates());
            if(distanceInThisIteration < minimumDistanceFound){
                minimumDistanceFound = distanceInThisIteration;
                closestEnemyIndex = i;
            }
        }

        return closestEnemyIndex;
    }
}
