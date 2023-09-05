package queue;

// Model
public interface Queue {
    //        Pred: element != null && size queue <= max size
    //        Post: n' = n + 1 && a[n'] == element && tail = (tail + 1) % n && for i=1..n: a'[i] == a[i]
    void enqueue(Object element);
    //        Pred: n > 0
    //        Post: R == a[1] && n' == n - 1 && for i=1..n-1: a'[i] == a[i+1]
    Object dequeue();
    //    Pred: n > 0
    //    Post: R == a[n] && for i=1..n: a'[i] == a[i] && n' == n
    Object element();
    //        Pred: true
    //        Post: n - размер очереди && return size == n && return size >= 0 && if size == 0: return 0 && n' == n && for i=1..n: a'[i] == a[i]
    int size();
    //        Pred: true
    //        Post: return size == (n == 0) && n' == n && for i=1..n: a'[i] == a[i]
    boolean isEmpty();
    //        Pred: true
    //        Post: size = 0
    void clear();

    int count(Object element);
}
