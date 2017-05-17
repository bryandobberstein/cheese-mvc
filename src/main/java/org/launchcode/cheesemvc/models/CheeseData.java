package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

/**
 * Created by max on 5/17/17.
 */
public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    public static ArrayList getAll(){
        return cheeses;
    }

    public static void add(Cheese newCheese){
        cheeses.add(newCheese);
    }

    public static void remove(int id){
        Cheese rCheese = getById(id);
        cheeses.remove(rCheese);
    }

    public static Cheese getById(int id){
        Cheese aCheese = null;
        for (Cheese pCheese : cheeses){
            if (pCheese.getCheeseId() == id){
                aCheese = pCheese;
            }
        }
        return aCheese;
    }
}
