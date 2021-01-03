package com.hillel.artemjev.wordcount;

import com.hillel.artemjev.wordcount.map.MyMap;


public class WordCounter {
    public static MyMap<String, Integer> getWords(String text) {
        String[] arrWords = text.toUpperCase().split("[\\s,.!?;()\"-]+");
        MyMap<String, Integer> mapWords = new MyMap<>();

        for (String word : arrWords) {
            if (!word.isBlank()) {
                Integer wordCount = mapWords.get(word);
                mapWords.put(word, wordCount != null ? ++wordCount : 1);
            }
        }
        return mapWords;
    }
}
