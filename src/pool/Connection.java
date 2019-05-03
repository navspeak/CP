package pool;

import java.util.concurrent.TimeUnit;

public class Connection {

	private String url;
	private long id;
	public Connection(String url, long id) {
		this.url = url;
		this.id = id;
		System.out.printf("Starting creating connection with url %s...\n", url);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Finished creating connection with url %s...\n", url);
	}

	
	@Override
	public String toString() {
		return "Connection [url=" + url + ", id=" + id + "]";
	}
	
}
