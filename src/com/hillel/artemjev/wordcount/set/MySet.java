package com.hillel.artemjev.wordcount.set;

import java.util.Iterator;


public class MySet<T> implements Iterable<T> {
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
    public MySet(int size) {
        this.buckets = new Node[size];
    }

    public MySet() {
        this(16);
    }

    public void add(T elem) {
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

    public boolean contains(T elem) {
        return findNode(elem, getBucketIndex(elem)) != null;
    }

    public T get(int index) {
        int i = 0;
        for (T elem : this) {
            if (i == index) return elem;
            i++;
        }
        return null;
    }

    //-------------------------------------------
    //костыль
    public T find(T elem) {
        int index = getBucketIndex(elem);
        Node existElem = findNode(elem, index);
        return existElem != null ? (T) existElem.value : null;
    }
    //-------------------------------------------

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int curBucket = -1;
            private Node cur = null; // ??? вроде можно и без " ... = null"

            @Override
            public boolean hasNext() {

                if (cur != null && cur.next != null) {
                    cur = cur.next;
                    return true;
                }

                for (++curBucket; curBucket < buckets.length; ++curBucket) {
                    if (buckets[curBucket] != null) {
                        cur = buckets[curBucket];
                        return true;
                    }
                }

                return false;
            }

            @Override
            public T next() {
                return (T) cur.value;
            }
        };
    }

    //-------------------------------------------
    private int getBucketIndex(T elem) {
        return Math.abs(elem.hashCode()) % buckets.length;
    }

    private Node findNode(T elem, int bucketIndex) {
        for (Node cur = buckets[bucketIndex]; cur != null; cur = cur.next) {
            if (cur.value.equals(elem)) return cur;
        }
        return null;
    }
}