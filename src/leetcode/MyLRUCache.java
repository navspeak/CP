package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MyLRUCache<K,V> {
	
	private HashMap<K, V> map;
	private static final float load = 0.75f;
	private int capacity;
	
	public MyLRUCache(int c) {
		int capacity = c;
		int hashTablecapacity = (int)(Math.ceil(c/load)) + 1;
		map = new LinkedHashMap<>(hashTablecapacity, load, true){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(java.util.Map.Entry<K,V> eldest) {
				return size() > capacity;
			};
		};
		
	}
	
	public V get(K key) {
		return map.get(key);
	}
	
	public void put(K key, V val){
		map.put(key, val);
	}
	
	public void remove (K key) {
		map.remove(key);
	}
	
	public int usedEnteries() {
		return map.size();
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
	//https://medium.com/@mr.anmolsehgal/java-linkedhashmap-internal-implementation-44e2e2893036
	public static void main(String[] args) {
		MyLRUCache<String, String>  cache= new MyLRUCache<>(3);
		cache.put("1", "One"); 
		cache.put("2", "two");
		cache.put("3", "three");
		System.out.println(cache); //{1=One, 2=two, 3=three} // oldest .... Newest
		cache.get("2");
		System.out.println(cache); // {1=One,  3=three, 2=two}
		cache.put("4", "Four");
		System.out.println(cache); // {3=three, 2=two, 4=four}
		cache.put("4", "FOUR");
		System.out.println(cache); // {3=three, 2=two, 4=FOUR}
		
		HashMap<String, String> map1= new HashMap<String, String>();
		map1.put("1", "ONE");
		map1.put("1", "ONE");
		System.out.println(map1);
		
	}

}
