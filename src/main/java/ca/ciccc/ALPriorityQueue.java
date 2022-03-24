package ca.ciccc;

import java.util.ArrayList;

public class ALPriorityQueue<K extends Comparable, V> implements VCPriorityQueue<K, V> {
    public static final Entry DEFAULT_PRIORITY_ENTRY = new Entry(Integer.MAX_VALUE, "");
    private ArrayList<Entry> arrList = new ArrayList<>();

    @Override  public int size() {
        return arrList.size();
    }
    @Override public boolean isEmpty() {
        return arrList.isEmpty();
    }
    @Override public Entry<K, V> enqueue(K key, V value) throws IllegalArgumentException {
        Entry<K,V> entry = new Entry<K,V>(key, value);
        return arrList.add(entry) ? entry : null;
    }
    @Override public Entry peek() {
        Entry priorityEntry = DEFAULT_PRIORITY_ENTRY;
        for (Entry entry : arrList) priorityEntry = priorityEntry.key.compareTo(entry.key) > 0 ? entry : priorityEntry;
        return priorityEntry;
    }
    @Override public Entry dequeueMin() {
        Entry priorityEntry = peek();
        return arrList.remove(priorityEntry) ? priorityEntry : null;
    }
    @Override public VCPriorityQueue merge(VCPriorityQueue other) {
        while (!other.isEmpty()) arrList.add(other.dequeueMin());
        return this;
    }
}