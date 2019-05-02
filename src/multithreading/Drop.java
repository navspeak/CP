package multithreading;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Drop {

    final int size;
    final int[] store;
    int count = 0;
    Drop(int size) {
        this.size = size;
        store = new int[size];
    }

    public synchronized void put(int x) throws InterruptedException {
        while (count == size - 1) {
            wait();
        }
        store[count++] = x;
        notify();

    }

    public synchronized int get() throws InterruptedException{
        while(count == 0) {
            wait();
        }
        int ret = store[--count];
        notify();
        return ret;
    }

    public static void main(String[] args) {
        Drop drop = new Drop(5);
        Prod p = new Prod(drop);
        Cons c = new Cons(drop);
        Thread T1 = new Thread(p);
        Thread T2 = new Thread(c);

        T1.start();
        T2.start();
    }
}

class Prod implements Runnable{
    Drop drop;
    volatile boolean stopped = false;
    Prod(Drop drop) {this.drop = drop;}
    AtomicInteger i = new AtomicInteger(0);
    @Override
    public void run() {
        while(i.get() < 15){
            int val = new Random().nextInt(10);
            try {
                System.out.println("Produced " + val) ;
                drop.put(val);
                ProducerConsumer.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i.incrementAndGet();
        }
    }
}


class Cons implements Runnable{
    Drop drop;
    Cons(Drop drop) {this.drop = drop;}
    AtomicInteger i = new AtomicInteger(0);
    @Override
    public void run() {
        while(i.get() < 15){
            try {
                System.out.println("Consumed " +
                        drop.get());
                ProducerConsumer.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i.incrementAndGet();
        }
    }
}