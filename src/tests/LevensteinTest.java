/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class LevensteinTest {
    
 
    public LevensteinTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDistance method, of class Levenstein.
     */
    @Test
    public void testGetDistance() {
        System.out.println("getDistance");
        //String str1 = "kitten";//exp=3
        //String str2 = "sitting";
        String str1 = "aaaz";
        String str2 = "aaaz11";
        int expResult = 2;
        int result = Levenstein.getDistance(str1, str2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNormalisedDistance method, of class Levenstein.
     */
    @Test
    public void testGetNormalisedDistance() {
        System.out.println("getNormalisedDistance");
        String str1 = "aaaz";
        String str2 = "aaaz22";
        Levenstein instance = new Levenstein();
        double expResult = 1.0 - 2.0/6.0;
        double result = instance.getNormalisedDistance(str1, str2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
