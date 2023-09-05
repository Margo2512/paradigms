package queue;

import java.util.Objects;
public class ArrayQueue extends AbstractQueue {
    private Object[] elements;
    private int head;
    public ArrayQueue() {
        elements = new Object[2];
    }

    public static ArrayQueue create() {
        final ArrayQueue queue = new ArrayQueue();
        queue.elements = new Object[2];
        return queue;
    }
    @Override
    protected void enqueueImpl(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(this, this.size + 1);
        this.elements[(this.head + this.size) % this.elements.length] = element;
    }
    private Object[] unification(ArrayQueue queue, Object[] newElements, int size) {
        for (int i = 0; i < size; i++) {
            newElements[i] = queue.elements[(queue.head + i) % queue.elements.length];
        }
        return newElements;
    }
    private void ensureCapacity(ArrayQueue queue, int size) {
        if (queue.elements.length < size) {
            Object[] newElements = new Object[2 * size];
            unification(queue, newElements, size);
            queue.elements = newElements;
            queue.head = 0;
        }
    }
    public Object[] toArray(ArrayQueue this) {
        Object[] result = new Object[this.size];
        result = unification(this, result, this.size);
        return result;
    }

    @Override
    protected Object elementImpl() {
        return this.elements[head];
    }

    @Override
    protected void dequeueImpl() {
        elements[head] = null;
        head = (head + 1) % elements.length;
    }
}
