package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

     public void testEmptyArray() {
    	ArrayList<String> names = new ArrayList<>();//empty player name list
	ArrayList<Integer> coins = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
	ArrayList<Integer> gems = new ArrayList<>(Arrays.asList(5, 3, 2, 1));
	assertFalse(new App().printOrdered(names, coins, gems, 10));
    }
	
    
    public void testNegativeGemVal() {
	ArrayList<String> names = new ArrayList<>(Arrays.asList("ayşe", "ali", "ahmet", "fatma"));
	ArrayList<Integer> coins = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
	ArrayList<Integer> gems = new ArrayList<>(Arrays.asList(5, 3, 2, 1));
	assertFalse(new App().printOrdered(names, coins,gems,-1));//negative gemstone value -1
    }
    /*
    public void testEquality() {
	ArrayList<String> names = new ArrayList<>(Arrays.asList("ayşe", "ali", "ahmet", "fatma"));
	ArrayList<Integer> coins = new ArrayList<>(Arrays.asList(10, 10, 10, 10));
	ArrayList<Integer> gems = new ArrayList<>(Arrays.asList(1, 1, 1, 1));
	App app = new App();
	app.printOrdered(names, coins,gems,1);
	assertEquals("ayşe, ali, ahmet, fatma",app.getRichest());//valid inputs equality case
    }
    */
    public void testOneRich() {
	ArrayList<String> names = new ArrayList<>(Arrays.asList("ayşe", "ali", "ahmet", "fatma"));
	ArrayList<Integer> coins = new ArrayList<>(Arrays.asList(10, 10, 10, 10));
	ArrayList<Integer> gems = new ArrayList<>(Arrays.asList(100, 1, 1, 1));
	App app = new App();
	app.printOrdered(names, coins,gems,10);
	assertEquals("ayşe",app.getRichest());//valid inputs one richest
    }
     
    public void testNegativeInArrays() {
	ArrayList<String> names = new ArrayList<>(Arrays.asList("ayşe", "ali", "ahmet", "fatma"));
	ArrayList<Integer> coins = new ArrayList<>(Arrays.asList(-10, 10, 10, 10));
	ArrayList<Integer> gems = new ArrayList<>(Arrays.asList(100, 1, 1, 1));
	assertFalse(new App().printOrdered(names, coins,gems,10));//negative coin 
    }
}
