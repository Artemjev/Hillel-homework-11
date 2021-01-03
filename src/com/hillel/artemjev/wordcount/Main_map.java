package com.hillel.artemjev.wordcount;

import com.hillel.artemjev.wordcount.map.MyMap;
import com.hillel.artemjev.wordcount.map.MyMapBasedOnSet;


public class Main_map {

    public static void main(String[] args) {

        MyMap<String, String> map = new MyMap<>(100);
//        MyMapBasedOnSet<String, String> map = new MyMapBasedOnSet<>(100);

        map.put("vasja", "student");
        map.put("petja", "student");
        map.put("oleg", "engineer");

        map.put("vasja", "engineer");
        System.out.println(map.get("vasja"));
        System.out.println(map.get("vasjjjjja"));

        System.out.println("---------------------------------------");

        for (String key : map.keySet()) {
            System.out.println(key);
        }

        System.out.println("---------------------------------------");

        for (MyMap.Pair<String, String> pair : map.pairSet()) {
            System.out.println(pair.getKey() + " => " + pair.getValue());
        }
    }
}
