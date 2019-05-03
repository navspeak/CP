package pool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoolObject {
	
    private static final long ALLOWED_IDLE_TIME = 1000;
	private ConcurrentLinkedQueue<PoolObject> pool; 
    private ScheduledExecutorService scheduler = null;
    private long lastAction;
	Runnable command = () -> {
		long currTime = System.currentTimeMillis();
		long idleTime = currTime - lastAction;
		if (idleTime > ALLOWED_IDLE_TIME) {
			this.close();
			this.pool.offer(this);
		}
	};
	
	public PoolObject(ConcurrentLinkedQueue<PoolObject>  pool, int delay) {
		this.pool = pool;
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(command, delay, delay, TimeUnit.SECONDS);
		lastAction = System.currentTimeMillis();
	}
	
	private void close() {
		// TODO Auto-generated method stub
		
	}

	public Object getStatement() {
		lastAction = System.currentTimeMillis();
		return null;
	}
	
	public boolean release() {
		//cleanup
		return pool.offer(this);
	}

}
