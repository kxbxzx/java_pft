package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Alexander on 18.03.2017.
 */
public class SquareTests {

    @Test
    public void testArea(){
        Square s = new Square(5);
        Assert.assertEquals(s.area(), 25.0);

    }
}
