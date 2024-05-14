

abstract class Unit extends SimulationObject
{
    protected double health;//health
    protected double range;//range
    protected double maxStepDistance;//max distance able to move per tick
    protected double tickPerAttack;//ticks between atack opportuniteis
    protected String team;//Team to which the unit belongs


    public Unit() {
        super();
        this.types.add(SimulationObjectType.UNIT);

        this.declaredNextCoordinates = this.coordinates;//This is important in case the object were to not move
    }
    //protected String type;//more like name of the unit  archer,knight

    @Override
    public void walkTickDeclareNext() {

        //First we search for closest enemy, to be exact to its position in SimplifiedList
        int closestEnemyIndex = findClosestEnemyIndex();

        //if none were found we return as we will only move when there are enemies
        if (closestEnemyIndex == -1){
            return;
        }

        // We will treat the var below as a vector describing relation between this unit and the enemy
        Coordinates deltaCoordinates = new Coordinates(SimulationEngine.SimpleSimulationObjectList.get(closestEnemyIndex).getCoordinates().x - this.coordinates.x, SimulationEngine.SimpleSimulationObjectList.get(closestEnemyIndex).getCoordinates().y - this.coordinates.y);
        double vectorLenght = Math.sqrt((Math.pow(deltaCoordinates.x,2) + Math.pow(deltaCoordinates.y,2)));

        if (vectorLenght < this.range) {// We don't move if we are already in range
            return;
        }

        //We want so move only as far as to be in range
        double desiredMovementStep = vectorLenght - this.range;

        // but we need to limit this to maxStepDistance
        if (desiredMovementStep > this.maxStepDistance){
            desiredMovementStep = this.maxStepDistance;
        }

        //We will divide the deltaCordinatex.x and .y by this number to lower the step size
        double mathConst = vectorLenght / desiredMovementStep;
        deltaCoordinates.x /= mathConst;
        deltaCoordinates.y /= mathConst;

        //Then we update the declaredNextCoordinates
        this.declaredNextCoordinates.x = this.coordinates.x + deltaCoordinates.x;
        this.declaredNextCoordinates.y = this.coordinates.y + deltaCoordinates.y;

        checkBoundries();
    }
    @Override
    public void walkTick(){
        this.coordinates = declaredNextCoordinates;
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

    private void checkBoundries(){
        if (this.declaredNextCoordinates.x > SimulationEngine.maxPositive.x){
            this.declaredNextCoordinates.x = SimulationEngine.maxPositive.x;
        }
        else if (this.declaredNextCoordinates.x < SimulationEngine.maxNegative.x){
            this.declaredNextCoordinates.x = SimulationEngine.maxNegative.x;
        }

        if (this.declaredNextCoordinates.y > SimulationEngine.maxPositive.y){
            this.declaredNextCoordinates.y = SimulationEngine.maxPositive.y;
        }
        else if (this.declaredNextCoordinates.y < SimulationEngine.maxNegative.y){
            this.declaredNextCoordinates.y = SimulationEngine.maxNegative.y;
        }
    }
}
