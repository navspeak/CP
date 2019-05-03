package pool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class MyObjectPool<T> {

    private ConcurrentLinkedQueue<T> pool;
    private ScheduledExecutorService executorService;

    public MyObjectPool() {
        this(5,10,5);
    }

    public MyObjectPool(int minObject, int maxObject, int delay) {
        pool = new ConcurrentLinkedQueue<T>();
        for (int i = 0; i < minObject; i++) {
            pool.add(createObject());
        }
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (pool.size() < minObject) {
                    for (int i = 0; i < minObject - pool.size(); i++) {
                        pool.add(createObject());
                    }

                } else if (pool.size() > maxObject) {
                    for (int i = 0; i < maxObject - pool.size(); i++) {
                        pool.remove();
                    }
                }

            }
        }, delay, delay, TimeUnit.SECONDS);

    }

    abstract T createObject();

    public T borrow() {
        T o;
        if ((o = pool.poll()) == null) {
            o = createObject();
        }
        return o;
    }

    public void returnToPool(T o){
        if (o == null) return;
        pool.offer(o);
    }

    public void shutDown() {
        executorService.shutdown();
    }

}

