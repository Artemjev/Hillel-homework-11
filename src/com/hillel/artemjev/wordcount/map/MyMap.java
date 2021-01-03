package com.hillel.artemjev.wordcount.map;

import com.hillel.artemjev.wordcount.set.MySet;


public class MyMap<K, V> {
    private Node[] buckets;
    private int size = 0;

    //-------------------------------------------
    private static class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }
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
            return key.equals(pair.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    //-------------------------------------------
    public MyMap(int size) {
        this.buckets = new Node[size];
    }

    public MyMap() {
        this(16);
    }

    public void put(K key, V value) {
        Pair<K, V> elem = new Pair<>(key, value);
        int bucketIndex = getBucketIndex(elem);
        Node existElem = findNode(elem, bucketIndex);
        if (existElem != null) {
            existElem.value = elem;
        } else {
            Node node = new Node(elem);
            node.next = buckets[bucketIndex];
            buckets[bucketIndex] = node;
            ++size;
        }
    }

    public boolean containsKey(K key) {
        Pair<K, V> elem = new Pair<>(key, null);
        return findNode(elem, getBucketIndex(elem)) != null;
    }

    public V get(K key) {
        Pair<K, V> elem = new Pair<>(key, null);
        int bucketIndex = getBucketIndex(elem);
        Node existElem = findNode(elem, bucketIndex);
        return existElem != null ? ((Pair<K, V>) existElem.value).value : null;
    }

    public MySet<K> keySet() {
        MySet<K> keySet = new MySet<>(buckets.length);

        for (int curBucket = 0; curBucket < buckets.length; ++curBucket) {
            for (Node curNode = buckets[curBucket]; curNode != null; curNode = curNode.next) {
                keySet.add(((Pair<K, V>) curNode.value).key);
            }
        }
        return keySet;
    }

    public MySet<Pair<K, V>> pairSet() {
        MySet<Pair<K, V>> pairSet = new MySet<>(buckets.length);

        for (int curBucket = 0; curBucket < buckets.length; ++curBucket) {
            for (Node curNode = buckets[curBucket]; curNode != null; curNode = curNode.next) {
                pairSet.add((Pair<K, V>) curNode.value);
            }
        }
        return pairSet;
    }

    //-------------------------------------------
    private int getBucketIndex(Pair<K, V> elem) {
        return Math.abs(elem.hashCode()) % buckets.length;
    }

    private Node findNode(Pair<K, V> elem, int bucketIndex) {
        for (Node cur = buckets[bucketIndex]; cur != null; cur = cur.next) {
            if (cur.value.equals(elem)) return cur;
        }
        return null;
    }
}