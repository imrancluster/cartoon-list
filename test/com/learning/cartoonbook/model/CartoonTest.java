package com.learning.cartoonbook.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartoonTest {
    private Cartoon cartoon;

    @Before
    public void setUp() throws Exception {
        cartoon = new Cartoon("Jony Jony", "Zhu Zhu TV", "http://www.youtub.com");
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Message from getName Function", "Jony Jony", cartoon.getName());
    }

}
