package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Demonstration {
    public static void main( String args[] ) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(5);
        TimeUnit.SECONDS.sleep(10);
        for (int i = 0; i < 10 ; i++) {
            executorService.submit(()-> tokenBucketFilter.getToken());
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.DAYS);
    }
}

class TokenBucketFilter {

    private int MAX_TOKENS;
    private long lastRequestTime = System.currentTimeMillis();
    long possibleTokens = 0;

    public TokenBucketFilter(int maxTokens) {
        MAX_TOKENS = maxTokens;
    }

    synchronized void getToken()  {

        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;

        if (possibleTokens > MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }

        if (possibleTokens == 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            possibleTokens--;
        }
        lastRequestTime = System.currentTimeMillis();

        System.out.println("Granting from " + (possibleTokens+1) + "tokens "
                + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
    }


}