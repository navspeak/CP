import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        Map<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1,"one");
        map.put(1, "ONE");
        map.put(1, "Un");

        final Iterator<Integer> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            System.out.println(map.get(iterator.next()));
            map.put(2,"Two");
        }



    }
}
