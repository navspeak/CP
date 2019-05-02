package multithreading;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;


class Demo {
    public static void main(String[] args) throws InterruptedException {
        BoundedQueue<Integer> q = new BoundedQueue<>(Integer.class, 5);
        Thread producerThread = new Thread( () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    q.enqueue(Integer.valueOf(i));
                    System.out.println("Producer Thread enqueued " + i);
                }
            } catch (InterruptedException ie) {

            }
        });

        Thread consumerThread1 = new Thread( () -> {
            try {
                for (int i = 0; i < 25; i++) {
                    System.out.println("Consumer Thread 1 dequeued: " + q.dequeue());
                }
            } catch (InterruptedException ie) {

            }
        });

        Thread consumerThread2 = new Thread( () -> {
            try {
                for (int i = 0; i < 25; i++) {
                    System.out.println("Consumer Thread 2 dequeued: " + q.dequeue());
                }
            } catch (InterruptedException ie) {

            }
        });

        producerThread.start();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {

        }
        consumerThread1.start();
        consumerThread1.join();
        consumerThread2.start();
        producerThread.join();
        consumerThread2.join();
    }
}

class BoundedQueue<T>{
    T[] items;
    int head = 0, tail = 0;
    int size = 0;
    final int capacity;

    BoundedQueue(Class<T> clazz, int capacity) {
        items = (T[]) Array.newInstance(clazz, capacity);
        this.capacity = capacity;
    }


    synchronized void enqueue(T item) throws InterruptedException {
        while (size == capacity) {
            wait();
        }
        if (tail == capacity)
            tail = 0;
        items[tail++] = item;
        size++;
        notify();
    }

    synchronized T dequeue() throws InterruptedException {
        while (size == 0){
            wait();
        }

        if (head == capacity){
            head = 0;
        }

        T ret = items[head];
        items[head] = null;
        head++;
        size--;
        notify();
        return ret;
    }

}