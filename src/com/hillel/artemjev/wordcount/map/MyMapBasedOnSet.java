package com.hillel.artemjev.wordcount.map;

import com.hillel.artemjev.wordcount.set.MySet;


public class MyMapBasedOnSet<K, V> {
    private MySet<Pair<K, V>> pairSet;

    public MyMapBasedOnSet(int size) {
        pairSet = new MySet<>(size);
    }

    public MyMapBasedOnSet() {
        this(16);
    }

    public void put(K key, V value) {
        Pair<K, V> elem = new Pair<>(key, value);
        pairSet.add(elem);
    }

    public boolean containsKey(K key) {
        Pair<K, V> elem = new Pair<>(key, null);
        return pairSet.contains(elem);
    }

    public V get(K key) {
        Pair<K, V> elem = new Pair<>(key, null);
        Pair<K, V> existElem = pairSet.find(elem);
        return existElem != null ? existElem.value : null;

//        return pairSet.find(elem) != null ? pairSet.find(elem).value : null;
//        Закоментированный код выше короче и не содержит создание/присваивание ссылки,
//        но при этом метод pairSet.find(elem) вызывается 2 раза.
//        Вопрос: может ли это быть эфективнее???
//        (почему-то кжаться, что jvm должна оптимизировать 2 вызова подряд метода pairSet.find(elem)
//        в тренарном операторе)
    }

    public MySet<Pair<K, V>> pairSet() {
        return this.pairSet;
    }

    //-------------------------------------------
    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
//            return Objects.equals(key, pair.key);
            return key.equals(pair.key);
        }

        @Override
        public int hashCode() {
//            return Objects.hash(key);
            return key.hashCode();
        }
    }
    //-------------------------------------------
}