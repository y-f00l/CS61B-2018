package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    public int capacity();     // return size of the buffer
    public int fillCount();    // return number of items currently in the buffer
    public void enqueue(T x);  // add item x to the end
    public T dequeue();        // delete and return item from the front
    public T peek();           // return (but do not delete) item from the front
    default boolean isEmpty() {
        return this.fillCount() == 0;
    }

    default boolean isFull() {
        return this.fillCount() == this.capacity();
    }

    public Iterator<T> iterator();
}
