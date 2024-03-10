package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App 
{
    
    static HashMap<String, Integer> player_value = new HashMap<>();
    static List<Map.Entry<String, Integer>> sortedList;
    static StringBuilder richest;
    static StringBuilder list;
    
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));
	  
	  //input1 için
          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          java.util.ArrayList<String> nameList = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            String name = sc1.next();
            nameList.add(name);
          }
          System.out.println(nameList);

	  //input2 için
          String input2 = req.queryParams("input2");
          java.util.Scanner sc2 = new java.util.Scanner(input2);
          sc2.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> coinList = new java.util.ArrayList<>();
          while (sc2.hasNext())
          {
            int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
            coinList.add(value);
          }
          System.out.println(coinList);
          //input3 için
          String input3 = req.queryParams("input3");
          java.util.Scanner sc3 = new java.util.Scanner(input3);
          sc3.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> gemList = new java.util.ArrayList<>();
          while (sc3.hasNext())
          {
            int value = Integer.parseInt(sc3.next().replaceAll("\\s",""));
            gemList.add(value);
          }
          System.out.println(gemList);
          //input4 için
          String input4 = req.queryParams("input4").replaceAll("\\s","");
          int gemValue = Integer.parseInt(input4);
          System.out.println(gemValue);
          
          boolean list = App.printOrdered(nameList, coinList, gemList, gemValue);

         Map map = new HashMap();
          map.put("richest", richest.toString());
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) -> {
              Map map = new HashMap();
              map.put("richest", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
   
    public static boolean addedToHashMap(ArrayList<String> playerNames,ArrayList<Integer> playerCoins,ArrayList<Integer> playerGems,int gemValue) {
    System.out.println("let's start counting!");
    richest = new StringBuilder();
    list   = new StringBuilder();
    int commonSize=playerNames.size();
	if (playerNames == null || playerCoins == null || playerGems == null) return false;//we don't want arraylists to be null(nullpointer exception)
	if (playerCoins.size()!=commonSize || playerGems.size()!=commonSize) return false;
	if (gemValue<=0) return false;
	//gem ya da coin negatif olabilir mi? (emin değilim)
	    for (int i=0;i<playerNames.size();i++) {
	    	 String name=playerNames.get(i);
	    	 int total=playerCoins.get(i)+playerGems.get(i)*gemValue;
		 player_value.put(name,total);
	    }
        sortedList = new ArrayList<>(player_value.entrySet());
        Collections.sort(sortedList, Collections.reverseOrder(Map.Entry.comparingByValue()));
    return true;
    }
    public static boolean printOrdered(ArrayList<String> playerNames,ArrayList<Integer> playerCoins,ArrayList<Integer> playerGems,int gemValue){
    	if(!addedToHashMap(playerNames, playerCoins, playerGems, gemValue)) return false;
    	int point=sortedList.get(0).getValue();//*greatest value
    	int i=1;
    	for(Map.Entry<String, Integer> entry : sortedList){
	    if(entry.getValue()==point)//equality case
	       richest.append(entry.getKey()+" ");
    	   list.append((i++)+"."+entry.getKey()+":  "+entry.getValue()+"\n");
    	}
    	System.out.println("richest:\n"+richest.toString());
    	System.out.println("list:\n"+list.toString());
    return true;
    }  
}
