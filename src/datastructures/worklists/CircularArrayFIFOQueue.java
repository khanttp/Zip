package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {
    private E[] arr;
    private int front;
    private int back;
    private int len;

    public CircularArrayFIFOQueue(int capacity) {

        // Constructs circular array with this capacity

        // Indicate that parent constructor should take this parameter capacity
        super(capacity);
        this.len = 0;
        this.arr =  (E[])new Comparable[capacity];
        this.front = 0;
        this.back = 0;
    }

    @Override
    public void add(E work) {

        if (isFull()) {
            throw new IllegalStateException();
        } else {

            // Add to back of array
            this.arr[back] = work;

            // Wrap around if back pointer goes beyond capacity
            back = (back+1) % this.arr.length;
            this.len++;
        }

    }

    @Override
    public E peek() {

        if (!hasWork()) {
            throw new NoSuchElementException();
        } else {
            return this.arr[front];
        }
    }

    @Override
    public E peek(int i) {
        if (!hasWork()) {
            throw new NoSuchElementException();
        } else if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.arr[(front+i)%this.arr.length];
        }
    }

    @Override
    public E next() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        } else {

            // Save element to bge removed
            E element = this.arr[front];

            // Remove value at front and advance front pointer
            this.arr[front] = null;
            front = (front+1) % this.arr.length;
            this.len--;
            return element;
        }

    }

    @Override
    public void update(int i, E value) {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }

        if (i < 0 || i >= this.size()) {
            throw new IndexOutOfBoundsException();
        } else {

            // Update ith element and wrap around if needed
            this.arr[(front+i)%this.arr.length] = value;
        }

    }



    @Override
    public int size() {

        return this.len;
    }



    @Override
    public void clear() {
        this.len = 0;
        this.front = 0;
        this.back = 0;
    }




    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        }
        else {
            // Uncomment the line below for p2 when you implement equals
            // FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }
}
