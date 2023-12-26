package nonlinear;

import java.util.NoSuchElementException;

public class PriorityQueue {
    private final BinaryHeap heap;

    public PriorityQueue(int capacity) {
        heap = new BinaryHeap(capacity);
    }

    public PriorityQueue(int capacity, boolean isMinPriority) {
        heap = new BinaryHeap(capacity, isMinPriority);
    }

    public void enqueue(int element) {
        heap.insert(element);
    }

    public int dequeue() {
        if (!isEmpty()) {
            return heap.extractMinOrMax();
        }
        throw new NoSuchElementException("Priority queue is empty");
    }

    public int peek() {
        if (!isEmpty()) {
            return heap.getMinOrMax();
        }
        throw new NoSuchElementException("Priority queue is empty");
    }

    public int size() {
        return heap.getSize();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
