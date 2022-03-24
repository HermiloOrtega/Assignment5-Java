package ca.ciccc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BHPriorityQueueTest {
    BHPriorityQueue queue;
    BHPriorityQueue queueAux;

    @Before public void setUp() throws Exception {
        queue = new ALPriorityQueue();
        queueAux = new ALPriorityQueue();
    }
    @After public void tearDown() throws Exception {
    }
    @Test public void size() {
        assertEquals(0, queue.size());

        queue.enqueue(2, "two");
        queue.enqueue(1, "one");
        queue.enqueue(3, "three");
        queue.enqueue(4, "four");
        queue.enqueue(5, "five");
        assertEquals(3, queue.size());

        queue.peek();       assertEquals(5, queue.size());
        queue.dequeueMin(); assertEquals(4, queue.size());
    }
    @Test public void isEmpty() {
        assertTrue(queue.isEmpty());

        queue.enqueue(4, "four");   assertFalse(queue.isEmpty());
        queue.dequeueMin();         assertTrue(queue.isEmpty());
        queue.enqueue(1, "one");    assertFalse(queue.isEmpty());
        queue.peek();               assertFalse(queue.isEmpty());
    }
    @Test public void enqueue() {
        Entry entry = queue.enqueue(4, "four");
        assertEquals(key, entry.key);
        assertEquals(value, entry.value);
    }
    @Test public void peek() {
        queue.enqueue(4, "four"); Entry expected = queue.enqueue(1, "one");
        queue.enqueue(2, "two");  Entry result   = queue.peek();

        assertEquals(expected.key, result.key);
        assertEquals(expected.value, result.value);

        result = queue.peek();
        assertEquals(expected.key, result.key);
        assertEquals(expected.value, result.value);
    }
    @Test public void dequeueMin() {
        queue.enqueue(4, "four");
        Entry expected  = queue.enqueue(4, "four");
        Entry expected2 = queue.enqueue(5, "five");
        Entry result    = queue.dequeueMin();

        assertEquals(expected.key, result.key);
        assertEquals(expected.value, result.value);

        result   = queue.dequeueMin();
        expected = expected2;

        assertEquals(expected.key, result.key);
        assertEquals(expected.value, result.value);
    }
    @Test public void merge() {
        Entry result, expected, expected2;

        queue.enqueue(1, "one"); expected = queue.enqueue(1, "one");
        queue.enqueue(2, "two"); result = queue.peek();
        assertEquals(expected.key, result.key);
        assertEquals(expected.value, result.value);

        queueAux.enqueue(5, "five"); expected2 = queueAux.enqueue(0, "zero");
        queueAux.enqueue(2, "two");  result = queueAux.peek();
        assertEquals(expected2.key, result.key);
        assertEquals(expected2.value, result.value);

        VCPriorityQueue queueMerged = queue.merge(queueAux);

        result = queueMerged.peek();
        assertNotEquals(expected.key, result.key);
        assertNotEquals(expected.value, result.value);

        assertEquals(expected2.key, result.key);
        assertEquals(expected2.value, result.value);
        assertEquals(queue, queueMerged);
    }
}