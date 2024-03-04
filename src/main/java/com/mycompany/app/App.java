package com.mycompany.app;

import java. util. ArrayList;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {	
    	System.out.println( "Hello World!" );
    	
    	ArrayList<Integer> arr=new ArrayList<Integer>();
    	for(int i=0;i<10;i++)
    	arr.add(i);
        System.out.println("searched anf found: "+search(arr,3));
    }
    public static boolean search(ArrayList<Integer> array, int e) {
        System.out.println("inside search");
        if (array == null) return false;
  
        for (int elt : array) {
          if (elt == e) return true;
        }
        return false;
      }
}
