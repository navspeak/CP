package stacks;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    //public function
    private List<String> permutate(String str) {
        List<String> output = new ArrayList<>();
        permutate(str, output);
        return output;
    }

    // real logic
    private void permutate(String str, List<String> output) {
        // Consider string abc
        // seperate it into a and bc
        // permutate on bc and get all permutations list
        // add a at all positions in String got at above step

        if (str.length() == 1) {
            output.add(str);
            return;
        }

        String firstChar = str.substring(0,1);
        String remString = str.substring(1, str.length());
        List<String> subList = new ArrayList<>();
        permutate(remString, subList);
        for(String s1 : subList) {
            List<String> tmp = insertAtAllIndex(firstChar, s1);
            output.addAll(tmp);
        }
    }

    //helper function
    private List<String> insertAtAllIndex(String firstChar, String str) {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i <= str.length(); i++) {
            StringBuffer sb = new StringBuffer(str);
            sb.insert(i,firstChar);
            ret.add(sb.toString());
        }
        return ret;
    }

    public static void main(String[] args) {
        Permutation perm = new Permutation();
        System.out.println(perm.insertAtAllIndex("x","abc"));
        System.out.println(perm.permutate("abc"));
    }


}
