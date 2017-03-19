package Homework.Tasks;

/**
 * Created by Alexander on 11.03.2017.
 */
public class DistanceBetweenPoints{

    public static void main(String[] args){

        Point p1 = new Point(5, 15);
        Point p2 = new Point(3, 9);

        System.out.println(distance(p1, p2));
        System.out.println(p1.DistanceToPoints(p2));
    }

    //
    //Этот метод считает между двумя точками
    //
    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }
}




