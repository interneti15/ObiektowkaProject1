abstract class Projectile extends Unit{// unit that dies when atacks or when arrived at destination

    Projectile(){
        super();
        this.types.add(SimulationObjectType.PROJECTILE);
    }
    private Coordinates destination;

    public void shoot(){};
}
