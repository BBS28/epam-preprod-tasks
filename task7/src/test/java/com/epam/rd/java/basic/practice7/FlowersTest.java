package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Flowers;
import org.junit.Assert;
import org.junit.Test;

public class FlowersTest {
    @Test
    public void flowersShouldNotBeNull(){
        Flowers flowers = new Flowers();
        Assert.assertNotNull(flowers);
    }
}
