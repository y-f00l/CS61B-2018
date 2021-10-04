package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* The number of element in this ArrayRingBuffer */

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];

        /* init the first and last variable to point the position */
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        /* Don't insert the null to the RingBuffer */
        if (x == null) {
            return;
        }
        /* Don't insert to the full RingBuffer */
        if (this.fillCount == this.rb.length) {
            throw new RuntimeException("Ring Buffer overflow");
        }

        /* insert the element */
        this.rb[last] = x;
        this.fillCount += 1;

        /* make sure the RingBuffer */
        this.last = (this.last + 1) % (this.rb.length);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        /* Can't remove a element from a empty RingBuffer */
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }

        /* keep the count correct */
        fillCount -= 1;
        T result = rb[first];
        /* the circle */
        first = (first + 1) % (rb.length);
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return this.rb[first];
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos;
        private int width;

        public ArrayRingBufferIterator() {
            pos = 0;
            width = capacity();
        }

        @Override
        public boolean hasNext() {
            return pos == first;
        }

        public T next() {
            T result = rb[pos];
            pos = (pos + 1) % width;
            return result;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    /* For the equals to two ArrayRingBuffer */

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) obj;
        if (o.capacity() != this.capacity() || o.fillCount() != this.fillCount()) {
            return false;
        }

        Iterator<T>it = this.iterator();
        Iterator<T>other = o.iterator();

        while (it.hasNext() && other.hasNext()) {
            T item1 = it.next();
            T item2 = other.next();
            if (item1.equals(item2)){
                return false;
            }
        }

        return true;
    }
}
