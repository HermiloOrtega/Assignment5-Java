package ca.ciccc;

import java.util.Iterator;
import java.util.LinkedList;

public class BHPriorityQueue<K extends Comparable, V> implements VCPriorityQueue<K, V> {
    public static final Entry DEFAULT_PRIORITY_ENTRY = new Entry(Integer.MAX_VALUE, "");
    private LinkedList<Entry> linkList = new LinkedList<>();

    @Override public int size() {
        return linkList.size();
    }
    @Override public boolean isEmpty() {
        return linkList.isEmpty();
    }
    @Override public Entry<K, V> enqueue(K key, V value) throws IllegalArgumentException {
        int index = 0;
        Entry newEntry = new Entry(key, value);
        Iterator<Entry> iterator = linkList.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.key.compareTo(newEntry.key) > 0) break;
            index++;
        }
        linkList.add(index, newEntry);
        return newEntry;
    }
    @Override public Entry peek() {
        return linkList.peek();
    }
    @Override public Entry dequeueMin() {
        return linkList.poll();
    }
    @Override public VCPriorityQueue<K, V> merge(VCPriorityQueue<K, V> other) {
        int index = 0;
        Iterator<Entry> iterator = null;
        while (!other.isEmpty()) {
            iterator = linkList.listIterator(index);
            Entry newEntry = other.dequeueMin();
            while (iterator.hasNext()) {
                Entry entry = iterator.next();
                if (entry.key.compareTo(newEntry.key) > 0) break;
                index++;
            }
            linkList.add(index++, newEntry);
        }
        return this;
    }
}