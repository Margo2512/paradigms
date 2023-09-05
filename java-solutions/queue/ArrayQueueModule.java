package queue;
//Model: a[1]..a[n], head - первый индекс в очереди, tail - последний индекс очереди, size - количество элементов в очереди
//Invariant: n >= 0 && for i=1..n: a[i] != null

import java.util.Objects;

    public class ArrayQueueModule {
        private static Object[] elements = new Object[2];
        private static int size;
        private static int head;


//        Pred: element != null && size queue <= max size
//        Post: n' = n + 1 && a[n'] == element && tail = (tail + 1) % n && for i=1..n: a'[i] == a[i]
        public static void enqueue(final Object element) {
            Objects.requireNonNull(element);
            ensureCapacity(size + 1);
            elements[(head + size++) % elements.length] = element;
        }
        private static Object[] unification(Object[] newElements, int size) {
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(head + i) % elements.length];
            }
            return newElements;
        }
        private static void ensureCapacity(int size) {
            if (elements.length < size) {
                Object[] newElements = new Object[2 * size];
                unification(newElements, size);
                elements = newElements;
                head = 0;
            }
        }
        public static Object[] toArray() {
            Object[] result = new Object[size];
            result = unification(result, size);
            return result;
        }

//        Pred: n > 0
//        Post: R == a[n] && for i=1..n: a'[i] == a[i] && n' == n
        public static Object element() {
            assert size >= 1;
            return elements[head];
        }

//        Pred: n > 0
//        Post: R == a[1] && n' == n - 1 && for i=1..n-1: a'[i] == a[i+1]
        public static Object dequeue() {
            assert size >= 1;
            size--;
            Object result = elements[head];
            elements[head] = null;
            head = (head + 1) % elements.length;
            return result;
        }

//        Pred: true
//        Post: n - размер очереди && return size == n && return size >= 0 && if size == 0: return 0 && n' == n && for i=1..n: a'[i] == a[i]
        public static int size() {
            return size;
        }

//        Pred: true
//        Post: return size == (n == 0) && n' == n && for i=1..n: a'[i] == a[i]
        public static boolean isEmpty() {
            return size == 0;
        }

//        Pred: true
//        Post: size = 0 && head = 0 && for i=0..n: elements[i] == null.
        public static void clear() {
            size = 0;
            head = 0;
            for (int i = 0; i < elements.length-1; i++) {
                elements[i] = null;
            }
        }
    }