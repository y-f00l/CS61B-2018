package synthesizer;
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;
    public int capacity() {
        return this.capacity;
    }
    public int fillCount() {
        return this.fillCount;
    }
    public boolean isEmpty() {
        return this.fillCount == 0;
    }
    public boolean isFull() {
        return this.capacity == this.fillCount;
    }
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);
}
