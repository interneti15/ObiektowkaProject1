abstract class Unit extends SimulationObject
{
    protected double health;//health
    protected double range;//range
    protected double walkSpeed;//max distance able to move per tick
    protected double tickPerAttack;//ticks between atack opportuniteis
    protected String team;//Team to which the unit belongs


    public Unit(SimulationObjectType type) {
        super(type);
        this.isUnit = true;
    }
    //protected String type;//more like name of the unit  archer,knight

    @Override
    public void walkTick() {

        //First we search for closest enemy

        for (SimplifiedSimulationObject object :SimulationEngine.ObjectCoordinatesList){

        }
    }
    @Override
    public void attackTick() {

    }
    @Override
    public void afterTick() {

    }

}
