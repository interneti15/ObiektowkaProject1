abstract class Unit extends SimulationObject
{
    protected double health;//health
    protected double range;//range
    protected double walkSpeed;//max distance able to move per tick
    protected double tickPerAttack;//ticks between atack opportuniteis
    protected String team;//Team to which the unit belongs
    protected String type;//more like name of the unit  archer,knight

    @Override
    public void walkTick() {

    }
    @Override
    public void attackTick() {

    }
    @Override
    public void afterTick() {

    }

}
