package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;


public class StreamingMedian {

    public static void main(String[] args){
        //int[] arr = { 1,1,8,7,5,2,6,5, 7, 4, 10};
        int[] arr = { 11,2,8,7,5,2,6,5, 7, 4, 10};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(getStreamingMedian(arr)));

    }

    public static double[] getStreamingMedian(int[] arr){
//		PriorityQueue<Integer> lowers = new PriorityQueue<Integer>(new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				return -1 * o1.compareTo(o2);
//			}
//		}); // MaxHeap;
        PriorityQueue<Integer> lowers = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> highers = new PriorityQueue<Integer>(); // MinHeap;
        double[] medians = new double[arr.length];
        for (int i = 0; i < arr.length; i++){
            addNumber( arr[i], lowers, highers);
            balance(lowers,highers);
            medians[i] = getMedian(lowers, highers);
        }
        return medians;
    }

    private static void balance(PriorityQueue<Integer> lowers,
                                PriorityQueue<Integer> highers) {
        PriorityQueue<Integer> bigger = lowers.size() > highers.size() ? lowers: highers;
        PriorityQueue<Integer> smaller = lowers.size() < highers.size() ? lowers: highers;
        if (bigger.size() - smaller.size() < 2)
            return;
        smaller.add(bigger.remove());
    }

    private static double getMedian(PriorityQueue<Integer> lowers,
                                    PriorityQueue<Integer> highers) {
        System.out.print("Lower Heap : ");
        lowers.stream().forEach(num -> System.out.print(num +", "));
        System.out.println();
        System.out.print("Higher Heap : ");
        highers.stream().forEach(num -> System.out.print(num +", "));
        System.out.println();
        System.out.println("======================");
        if (lowers.size() == highers.size()){
            double ret = ((double)lowers.peek() + highers.peek())/2;
            return ret;
        }
        else if (lowers.size() > highers.size())
            return lowers.peek();
        else
            return highers.peek();
    }

    private static void addNumber(int num, PriorityQueue<Integer> lowers,
                                  PriorityQueue<Integer> highers) {
        if (lowers.size() == 0 && highers.size() == 0)
            lowers.add(num);
        else if (num > lowers.peek())
            highers.add(num);
        else
            lowers.add(num);
    }

}

//
//[1, 1, 8, 7, 5, 2, 6, 5, 7, 4, 10]
//Lower Heap : 1,
//Higher Heap :
//======================
//Lower Heap : 1,
//Higher Heap : 1,
//======================
//Lower Heap : 1,
//Higher Heap : 1, 8,
//======================
//Lower Heap : 1, 1,
//Higher Heap : 7, 8,
//======================
//Lower Heap : 1, 1,
//Higher Heap : 5, 8, 7,
//======================
//Lower Heap : 2, 1, 1,
//Higher Heap : 5, 8, 7,
//======================
//Lower Heap : 2, 1, 1,
//Higher Heap : 5, 6, 7, 8,
//======================
//Lower Heap : 5, 2, 1, 1,
//Higher Heap : 5, 6, 7, 8,
//======================
//Lower Heap : 5, 2, 1, 1,
//Higher Heap : 5, 6, 7, 8, 7,
//======================
//Lower Heap : 5, 4, 1, 1, 2,
//Higher Heap : 5, 6, 7, 8, 7,
//======================
//Lower Heap : 5, 4, 1, 1, 2,
//Higher Heap : 5, 6, 7, 8, 7, 10,
//======================
//[1.0, 1.0, 1.0, 4.0, 5.0, 3.5, 5.0, 5.0, 5.0, 5.0, 5.0]
