package queue;

public abstract class AbstractQueue implements Queue {

    protected int size;

    public Object element() {
        assert size >= 1;
        return elementImpl();
    }
    protected abstract Object elementImpl();

    public void enqueue(final Object element) {
        enqueueImpl(element);
        size++;
        assert !isEmpty();
    }
    public int count(Object element) {
        int count = 0;

        for (int i = 0; i < size(); i++) {
            Object o = dequeue();
            if (o.equals(element)) {
                count++;
            }
            enqueue(o);
        }
        return count;
    }

    public int indexOf(Object element) {
        int index = 0;

        for (int i = 0; i < size(); i++) {
            Object o = dequeue();
            if (o.equals(element)) {
                enqueue(o);
                return index;
            }
            enqueue(o);
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object element) {
        int lastIndex = -1;
        int index = 0;

        for (int i = 0; i < size(); i++) {
            Object o = dequeue();
            if (o.equals(element)) {
                lastIndex = index;
            }
            enqueue(o);
            index++;
        }
        return lastIndex;
    }

    public Object dequeue() {
        assert !isEmpty();
        Object result = element();
        dequeueImpl();
        size--;
        return result;
    }

    protected abstract void dequeueImpl();

    protected abstract void enqueueImpl(Object element);

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
        assert isEmpty();
    }
}
