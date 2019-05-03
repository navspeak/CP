package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

//ref: https://github.com/mission-peace/interview/blob/master/src/com/interview/geometry/SkylineDrawing.java

public class SkylineDrawing {

    /**
     * Represents either start or end of building
     */
    static class BuildingPoint {
        int x;
        boolean isStart;
        int ht;
        @Override
        public String toString() {
        	return "("+ x + "," + ht+")" + (isStart ? "S" : "E");
        }
        
     }
    
    public class BuildingComparator implements Comparator<BuildingPoint>{

		@Override
		public int compare(BuildingPoint o1, BuildingPoint o2) {
			 //first compare by x.
            //If they are same then use this logic
            //if two starts are compared then higher height building should be picked first (o2 - o1)
            //if two ends are compared then lower height building should be picked first (o1 - o2)
            //if one start and end is compared then start should appear before end 
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            } else {
                return (o1.isStart ? -o1.ht : o1.ht) - (o2.isStart ? -o2.ht : o2.ht);
            }
		}
    	
    }
    
    public List<int[]> getSkyline(int[][] buildings){
    	List<int[]> output = new ArrayList<int[]>();
    	List<BuildingPoint> buildingPoints = new ArrayList<BuildingPoint>();
    	for (int[] bld : buildings){
    		BuildingPoint pStart = new BuildingPoint();
    		//[1,2,3] = (1,3) start and (2,3) end
    		pStart.x = bld[0];
    		pStart.ht = bld[2];
    		pStart.isStart = true;
    		
    		BuildingPoint pEnd = new BuildingPoint();
    		//[1,2,3] = (1,3) start and (2,3) end
    		pEnd.x = bld[1];
    		pEnd.ht = bld[2];
    		pEnd.isStart = false;
    		
    		buildingPoints.add(pStart);
    		buildingPoints.add(pEnd);
    	}
    	
    	buildingPoints.sort(new BuildingComparator());
    	
    	buildingPoints.stream().forEach(bp -> System.out.println(bp));
//    	for (BuildingPoint bp : buildingPoints){
//    		System.out.println("("+bp.x + "," + bp.ht+")" + (bp.isStart ? "S" : "E"));
//    	}
//    	
    	SortedMap<Integer, Integer> map = new TreeMap<>();
    	map.put(0, 1);
    	int prevMaxHeight = 0;
    	
    	for (BuildingPoint pt : buildingPoints){
            //if it is start of building then add the height to map. If height already exists then increment
            //the value
    		System.out.println("Top of the loop: ");
    		System.out.println("---- Considering " + pt);
    		if (pt.isStart == true) {
    			map.compute(pt.ht, (key, value) -> { 
    				if (value!=null) {
    					System.out.println("------ value was " + value + ". Incrementing");
    					return value + 1;
    				}
					System.out.println("------ Didn't exist. Adding");
    				return 1;
    			});
    		} else {
            //if it is end of building then remove or decrement value for the height
    			map.compute(pt.ht, (key, value) -> { 
    				if (value == 1) {
    					System.out.println("------ value was 1. Removing");
    					return null;
    				}
					System.out.println("------ value was " + value + ". Decrementing");
    				return value - 1;
    			});
    		}
    		
    	   	int currMaxHeight = map.lastKey();
    	   	System.out.println("------ CurrMaxHeight " + currMaxHeight );
    	   	System.out.println("------ PrevMaxHeight " + prevMaxHeight );
    	   	if (prevMaxHeight != currMaxHeight){
        	   	System.out.println("------ Adding to output " + pt );
    	   		output.add(new int[]{pt.x, currMaxHeight});
                prevMaxHeight = currMaxHeight;
    	   	}
    	   	
    	}
    	return output;
    }
    
    public static void main(String[] args) {
    	int [][] buildings = {{1,3,4},{3,4,4},{2,6,2},{8,11,4}, {7,9,3},{10,11,2}};
    	System.out.println(buildings[0].length);
    	SkylineDrawing sd = new SkylineDrawing();
        List<int[]> criticalPoints = sd.getSkyline(buildings);
        criticalPoints.stream().forEach(cp -> System.out.println(cp[0] + " " + cp[1]));
//        for (int[] cp : criticalPoints){
//        	System.out.println(cp[0] + " " + cp[1]);
//        }
	}
    
//    (1,4)S
//    (2,2)S
//    (3,4)S
//    (3,4)E
//    (4,4)E
//    (6,2)E
//    (7,3)S
//    (8,4)S
//    (9,3)E
//    (10,2)S
//    (11,2)E
//    (11,4)E
//    Top of the loop: 
//    ---- Considering (1,4)S
//    ------ Didn't exist. Adding
//    ------ CurrMaxHeight 4
//    ------ PrevMaxHeight 0
//    ------ Adding to output (1,4)S
//    Top of the loop: 
//    ---- Considering (2,2)S
//    ------ Didn't exist. Adding
//    ------ CurrMaxHeight 4
//    ------ PrevMaxHeight 4
//    Top of the loop: 
//    ---- Considering (3,4)S
//    ------ value was 1. Incrementing
//    ------ CurrMaxHeight 4
//    ------ PrevMaxHeight 4
//    Top of the loop: 
//    ---- Considering (3,4)E
//    ------ value was 2. Decrementing
//    ------ CurrMaxHeight 4
//    ------ PrevMaxHeight 4
//    Top of the loop: 
//    ---- Considering (4,4)E
//    ------ value was 1. Removing
//    ------ CurrMaxHeight 2
//    ------ PrevMaxHeight 4
//    ------ Adding to output (4,4)E
//    Top of the loop: 
//    ---- Considering (6,2)E
//    ------ value was 1. Removing
//    ------ CurrMaxHeight 0
//    ------ PrevMaxHeight 2
//    ------ Adding to output (6,2)E
//    Top of the loop: 
//    ---- Considering (7,3)S
//    ------ Didn't exist. Adding
//    ------ CurrMaxHeight 3
//    ------ PrevMaxHeight 0
//    ------ Adding to output (7,3)S
//    Top of the loop: 
//    ---- Considering (8,4)S
//    ------ Didn't exist. Adding
//    ------ CurrMaxHeight 4
//    ------ PrevMaxHeight 3
//    ------ Adding to output (8,4)S
//    Top of the loop: 
//    ---- Considering (9,3)E
//    ------ value was 1. Removing
//    ------ CurrMaxHeight 4
//    ------ PrevMaxHeight 4
//    Top of the loop: 
//    ---- Considering (10,2)S
//    ------ Didn't exist. Adding
//    ------ CurrMaxHeight 4
//    ------ PrevMaxHeight 4
//    Top of the loop: 
//    ---- Considering (11,2)E
//    ------ value was 1. Removing
//    ------ CurrMaxHeight 4
//    ------ PrevMaxHeight 4
//    Top of the loop: 
//    ---- Considering (11,4)E
//    ------ value was 1. Removing
//    ------ CurrMaxHeight 0
//    ------ PrevMaxHeight 4
//    ------ Adding to output (11,4)E
//    1 4
//    4 2
//    6 0
//    7 3
//    8 4
//    11 0

}
