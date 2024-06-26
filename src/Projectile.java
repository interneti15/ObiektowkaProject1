

abstract class Projectile extends Unit{// unit that dies when atacks or when arrived at destination

    Projectile(Coordinates spawnCoordinates){
        super(spawnCoordinates);
        this.types.add(SimulationObjectType.PROJECTILE);
        this.health = 2;
        this.isImmortal = true;
    }

    protected Coordinates destination;
    protected int damage;

    @Override
    public void attackTick() {

        int closestEnemyIndex = findClosestEnemyIndex();
        if (closestEnemyIndex == -1){
            return;
        }

        if (Coordinates.distanceBetweenTwo(SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getCoordinates(), this.coordinates) <= this.range){

            int dmgTaken = SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).getDmgTaken();
            SimulationEngine.simpleSimulationObjectList.get(closestEnemyIndex).setDmgTaken(dmgTaken + this.damage);

            //We set projectile to some negative value so Engine will delete it
            this.health = ((-Double.MAX_VALUE))/2;
        }
    }

    @Override
    public void walkTickDeclareNext() {

        Coordinates deltaCoordinates = new Coordinates(this.destination.x - this.coordinates.x, this.destination.y - this.coordinates.y);
        double vectorLenght = Math.sqrt((Math.pow(deltaCoordinates.x,2) + Math.pow(deltaCoordinates.y,2)));

        double desiredMovementStep = vectorLenght;

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

        //If projectile is close enough to destination we set its health to some negative value so engine will delete it
        if (Coordinates.distanceBetweenTwo(this.declaredNextCoordinates, this.destination) < 1){
            this.health = ((-Double.MAX_VALUE))/2;
        }
    }

    @Override
    public Projectile copy() {

        Projectile copiedUnit = (Projectile) super.copy();
        copiedUnit.coordinates = new Coordinates(this.coordinates.x, this.coordinates.y);
        copiedUnit.damage = this.damage;
        return copiedUnit;

    }

}
