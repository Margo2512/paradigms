package queue;
import java.util.Objects;
public class ArrayQueueADT {
    private Object[] elements;
    private int size;
    private int head;
    public ArrayQueueADT() {
        elements = new Object[2];
    }

    public static ArrayQueueADT create() {
        final ArrayQueueADT queue = new ArrayQueueADT();
        queue.elements = new Object[2];
        return queue;
    }
//    Pred: element != null && size queue <= max size
//    Post: n' = n + 1 && a[n'] == element && tail = (tail + 1) % n && for i=1..n: a'[i] == a[i]
    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue, queue.size + 1);
        queue.elements[(queue.head + queue.size++) % queue.elements.length] = element;
    }
    private static Object[] unification(ArrayQueueADT queue, Object[] newElements, int size) {
        for (int i = 0; i < size; i++) {
            newElements[i] = queue.elements[(queue.head + i) % queue.elements.length];
        }
        return newElements;
    }
    private static void ensureCapacity(ArrayQueueADT queue, int size) {
        if (queue.elements.length < size) {
            Object[] newElements = new Object[2 * size];
            unification(queue, newElements, size);
            queue.elements = newElements;
            queue.head = 0;
        }
    }
    public static Object[] toArray(ArrayQueueADT queue) {
        Object[] result = new Object[queue.size];
        result = unification(queue, result, queue.size);
        return result;
    }

//    Pred: n > 0
//    Post: R == a[n] && for i=1..n: a'[i] == a[i] && n' == n
    public static Object element(ArrayQueueADT queue) {
        assert queue.size >= 1;
        return queue.elements[queue.head];
    }

//    Pred: n > 0
//    Post: R == a[1] && n' == n - 1 && for i=1..n-1: a'[i] == a[i+1]
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size >= 1;
        queue.size--;
        Object result = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return result;
    }

//    Pred: true
//    Post: n - размер очереди && return size == n && return size >= 0 && if size == 0: return 0 && n' == n && for i=1..n: a'[i] == a[i]
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

//    Pred: true
//    Post: return size == (n == 0) && n' == n && for i=1..n: a'[i] == a[i]
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

//    Pred: true
//    Post: size = 0 && head = 0 && for i=0..n: elements[i] == null.
    public static void clear(ArrayQueueADT queue) {
        queue.size = 0;
        queue.head = 0;
        for (int i = 0; i < queue.elements.length-1; i++) {
            queue.elements[i] = null;
        }
    }
}