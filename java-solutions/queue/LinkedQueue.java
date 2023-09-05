package queue;

import java.util.Objects;

public class LinkedQueue extends AbstractQueue {
    private Node head = null, tail = null;
    @Override
    protected void enqueueImpl(Object element) {
        if (head == null) {
            head = new Node(Objects.requireNonNull(element), null);
            tail = head;
        } else {
            tail.prev = new Node(Objects.requireNonNull(element), null);
            tail = tail.prev;
        }
    }
    @Override
    protected Object elementImpl() {
        return head.element;
    }
    @Override
    protected void dequeueImpl() {
        head = head.prev;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node {
        private final Object element;
        private Node prev;

        public Node(Object element, Node prev) {
            this.element = element;
            this.prev = prev;
        }
    }
}
