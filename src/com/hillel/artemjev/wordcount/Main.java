package com.hillel.artemjev.wordcount;

import com.hillel.artemjev.wordcount.counter.WordCounter;
import com.hillel.artemjev.wordcount.map.MyMap;


public class Main {

    public static void main(String[] args) {
        String str = "\"Я иду по парку, иду - один (я) и вижу! как по воде пробегает луч солнца.\"";

        System.out.println("Имеем строку:");
        System.out.println(str);
        System.out.println("\nДанная строка состоит из слов:");
        System.out.println("--------------------------------------");

        for (MyMap.Pair<String, Integer> pair : WordCounter.getCountWords(str).pairSet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }

        System.out.println("--------------------------------------");
    }
}
