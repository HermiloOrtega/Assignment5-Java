package ca.ciccc;

import java.util.LinkedList;
import java.util.List;

public class DLPriorityQueue<K extends Comparable, V> implements VCPriorityQueue {
    private List<Entry<K, V>> entries;

    public DLPriorityQueue() {
        this.entries = new LinkedList<Entry<K, V>>();
    }
    public DLPriorityQueue(List<Entry<K, V>> entries) {
        this.entries = entries;
    }

    public int size() {
        return this.entries.size();
    }
    @Override public boolean isEmpty() {
        return size() == 0;
    }
    @Override public Entry<K, V> enqueue(Comparable key, Object value) throws IllegalArgumentException {
        Entry<K, V> entry = new Entry<K, V>((K)key, (V)value);
        entries.add(entry);
        return entry;
    }
    public Entry<K, V> peek() {
        if (isEmpty()) return null;
        return this.entries.get(this.size() -1);
    }
    @Override public Entry<K, V> dequeueMin() {
        if (isEmpty()) return null;
        return this.entries.get(this.size() -1);
    }
    @Override public VCPriorityQueue merge(VCPriorityQueue other) {
        for (int i = 0; i < other.size(); i++ ) {
            Entry entry = other.dequeueMin();
            enqueue(entry.getKey(), entry.getValue());
        }
        return this;
    }
}