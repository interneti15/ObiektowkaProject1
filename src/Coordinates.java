import java.io.Serializable;

public class Coordinates implements Serializable {
    public double x;
    public double y;

    Coordinates(double X, double Y){
        this.x = X;
        this.y = Y;
    }
    Coordinates(){
        this.x = 0;
        this.y = 0;
    }
    public static double distanceBetweenTwo(Coordinates c1, Coordinates c2){
        return Math.sqrt(Math.pow((c1.x-c2.x),2)+Math.pow((c1.y-c2.y),2));
    }
    public static double distanceFrom00(Coordinates c1){
        return Math.sqrt(Math.pow((c1.x),2)+Math.pow((c1.y),2));
    }
}
