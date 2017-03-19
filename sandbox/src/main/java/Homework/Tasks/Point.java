package Homework.Tasks;

/**
 * Created by Alexander on 11.03.2017.
 */

public class Point{

    double x;
    double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    //
    // Считает от текущей точки к указанной
    //
    public double DistanceToPoints(Point p){
        return Math.sqrt(Math.pow((p.x - this.x), 2) + Math.pow((p.y - this.y), 2));
    }

}



