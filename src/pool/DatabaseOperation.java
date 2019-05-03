package pool;

public class DatabaseOperation implements Runnable {
	
	private ObjectPool<Connection> pool;
	private int threadNo;
	
	public DatabaseOperation(ObjectPool<Connection> pool, int threadNo) {
        this.pool = pool;
        this.threadNo = threadNo;
    }

	@Override
	public void run() {
		// get an object from the pool
        Connection connection = pool.borrowObject();

        System.out.println("Thread " + threadNo + 
                ": " + connection + " was borrowed");

        // do something
        // ...

        // for-loop is just for simulation
        for (int i = 0; i < 100000; i++) {
        }

        // return ExportingProcess instance back to the pool
        pool.returnObject(connection);

        System.out.println("Thread " + threadNo + 
                ": " + connection + " was returned");
		
	}

}
