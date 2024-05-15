
public class Coordinates{
    public double x;
    public double y;

    Coordinates(double X, double Y){
        this.x = X;
        this.y = Y;
    }
    public static double distanceBetweenTwo(Coordinates c1, Coordinates c2){
        return Math.sqrt(Math.pow((c1.x-c2.x),2)+Math.pow((c1.y-c2.y),2));
    }
}
