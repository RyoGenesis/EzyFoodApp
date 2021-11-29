package com.latihan.ezyfood_2301853962.dataset;

import com.latihan.ezyfood_2301853962.R;
import com.latihan.ezyfood_2301853962.model.Item;

import java.util.ArrayList;

public class OrderableItemData {

    private static String[] drinksName = {
            "Coffee",
            "Orange Juice",
            "Tea",
            "Ramune",
            "Soft Drink",
            "Root Beer"
    };

    private static int[] drinksPrice = {
            20000,
            11000,
            14000,
            25000,
            9000,
            21000
    };

    private static int[] drinksImg = {
            R.drawable.drink_coffee,
            R.drawable.drink_orange_juice,
            R.drawable.drink_tea,
            R.drawable.drink_ramune,
            R.drawable.drink_soft_drinks,
            R.drawable.drink_root_beer
    };

    public static ArrayList<Item> getDrinksData() {
        ArrayList<Item> list = new ArrayList<>();
        for (int pos = 0; pos < drinksName.length; pos++) {
            Item drink = new Item(drinksImg[pos],drinksName[pos],drinksPrice[pos],"drinks");
            list.add(drink);
        }
        return list;
    }

    private static String[] snacksName = {
            "Pempek",
            "Popcorn",
            "Pringles",
            "Sate",
            "Waffle",
            "Pretzel"
    };

    private static int[] snacksPrice = {
            18000,
            25000,
            17000,
            15000,
            18000,
            14000
    };

    private static int[] snacksImg = {
            R.drawable.snack_pempek,
            R.drawable.snack_popcorn,
            R.drawable.snack_pringles,
            R.drawable.snack_sate,
            R.drawable.snack_waffle,
            R.drawable.snack_pretzel
    };

    public static ArrayList<Item> getSnacksData() {
        ArrayList<Item> list = new ArrayList<>();
        for (int pos = 0; pos < snacksName.length; pos++) {
            Item snack = new Item(snacksImg[pos],snacksName[pos],snacksPrice[pos],"snacks");
            list.add(snack);
        }
        return list;
    }

    private static String[] foodsName = {
            "Hamburger",
            "Pizza",
            "Ramen",
            "Omurice",
            "Curry with Naan",
            "Steak"
    };

    private static int[] foodsPrice = {
            24000,
            50000,
            42000,
            35000,
            45000,
            60000
    };

    private static int[] foodsImg = {
            R.drawable.food_hamburger,
            R.drawable.food_pizza,
            R.drawable.food_ramen,
            R.drawable.food_omurice,
            R.drawable.food_curry_naan,
            R.drawable.food_steak
    };

    public static ArrayList<Item> getFoodsData() {
        ArrayList<Item> list = new ArrayList<>();
        for (int pos = 0; pos < foodsName.length; pos++) {
            Item food = new Item(foodsImg[pos],foodsName[pos],foodsPrice[pos],"foods");
            list.add(food);
        }
        return list;
    }

    public static ArrayList<Item> getData(String type){
        if(type.equals("drinks")){
            return getDrinksData();
        }
        else if(type.equals("snacks")){
            return getSnacksData();
        }
        else {
            return getFoodsData();
        }
    }
}
