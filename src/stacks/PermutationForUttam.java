package stacks;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


public class PermutationForUttam {
    static int recCount = 0;
    static Map<Integer, String> stackMap = new TreeMap<>();
    static void permutation(String str, int length, StringBuffer output, boolean[] used, int position){
        StringBuffer stackbuffer = new StringBuffer();
        stackbuffer.append("F( Str = " + str + " length = " +
                + length + ", output = " + output.toString() + ", used =  "
                + Arrays.toString(used) + ", pos = " +
                + position + ") ").append(System.lineSeparator());
        if (position==length){
            stackbuffer.append("END). Terminating condition hit (position == length), returning")
                    .append(System.lineSeparator());
            stackMap.put(recCount, stackbuffer.toString());
            recCount--;
            return;
        }
        else
        {
            for(int i=0;i<length;i++){
                stackbuffer.append("++ Inside For loop i = " + i).append(System.lineSeparator());

                /* skip already used characters */
                if (used[i]) {
                    continue;
                }


                /* add fixed character to output, and mark it as used */
                output.append(str.charAt(i));
                used[i]=true;

                /* permute over remaining characters starting at position+1 */
                recCount++;
                stackbuffer.append("+++ Recursing to stack frame  " + recCount)
                        .append("....").append(System.lineSeparator());
                permutation(str,length,output,used,position+1);

                /* remove fixed character from output, and unmark it */
                stackbuffer.append("+++ Returned back to  stack frame " + recCount).append(System.lineSeparator())
                        .append(System.lineSeparator());

                output.deleteCharAt(output.length()-1);

                used[i]=false;
            }
            stackMap.compute(recCount, (k,v) -> {
                if (v == null) {
                    return stackbuffer.toString();
                } else {
                    return stackbuffer.toString() + "\n" + v;
                }
            });
            recCount--;
        }
    }

    public static void permute(String str){
        int length=str.length();
        boolean[] used=new boolean[length];

        StringBuffer output=new StringBuffer(length);
        recCount++;
        permutation(str,length,output,used,recCount);
    }

    public static void main(String[] args){
        permute("abc");
        stackMap.forEach((k,v)-> {
            System.out.println("Stack Frame " + k);
            System.out.println("==============");
            System.out.println(v);
        });
    }
}

