package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {
    final static int MAX_SIZE = 5;
    final static Object lock = new Object();
    final static Queue<String> queue = new LinkedList<>();

    protected static void sleep(int x) {
        try {
            TimeUnit.SECONDS.sleep(x);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    static class Producer implements Runnable {
        final Queue<String> queue;
        volatile boolean stopped = false;

        Producer(Queue queue) {
            this.queue = queue;
        }


        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (Thread.interrupted() || stopped == true) {
                        stopped = true;
                        System.out.println("Producer Thread Cancelled");
                        break;
                    }
                    while (queue.size() == MAX_SIZE) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            stopped = true;
                        }
                    }
                    String produce = "Random String " + new Random().nextInt(10);
                    System.out.println("Producing " + produce);
                    queue.add(produce);
                    sleep(2);
                    lock.notify();
                }
            }
        }
    }
    static class Consumer implements Runnable {
        final Queue<String> queue;
        volatile boolean stopped = false;

        Consumer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            sleep(2);
            while (true) {
                synchronized (lock) {
                    if (Thread.interrupted() || stopped == true) {
                        stopped = true;
                        System.out.println("Consumer Thread Cancelled");
                        break;
                    }
                    while (queue.size() == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            stopped = true;
                        }
                    }
                    System.out.print("Queue size is " + queue.size() + ", ");
                    String val = queue.poll();
                    System.out.println("Consumed " + val);
                    sleep(2);
                    lock.notify();

                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread prodThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);


        System.out.println("Producer and Consumer simulation will start" +
                ". Press Y to cancel");
        sleep(2);
        prodThread.start();
        consumerThread.start();

        Scanner in = new Scanner(System.in);
        if (in.next().equalsIgnoreCase("Y")); {
            System.out.println("Cancelling....");
            prodThread.interrupt();
            consumerThread.interrupt();
        }

        prodThread.join();
    }
}
