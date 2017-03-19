package Homework.Tasks;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Alexander on 18.03.2017.
 */
public class PointTests {

    @Test
    public void testPoints01(){
        Point p1 = new Point(5, 15);
        Point p2 = new Point(3, 9);

        Assert.assertEquals(p1.DistanceToPoints(p2), 6.324555320336759);

    }

}
