package com.hillel.artemjev.wordcount.counter;

import com.hillel.artemjev.wordcount.map.MyMap;


public class WordCounter {
    public static MyMap<String, Integer> getCountWords(String text) {
        String regex = "[\\s,.!?;()\"-]+";
        String[] arrWords = text.toUpperCase().split(regex);
        MyMap<String, Integer> mapWordCount = new MyMap<>();

        for (String word : arrWords) {
            if (!word.isBlank()) {
                Integer wordCount = mapWordCount.get(word);
                mapWordCount.put(word, wordCount != null ? ++wordCount : 1);
            }
        }
        return mapWordCount;
    }
}
