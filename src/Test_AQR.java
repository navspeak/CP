import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test_AQR {
    public static void main(String[] args) {
        /*
                       (D)
             (P)       (D)          (X)
           (X) (D)  (D) (D) (X)
         */

        ParentNode root = new DocumentNode();



    }

    public static int getMinCost(ParentNode root){
        int c1 = Integer.MAX_VALUE, c2 = 0;
        Deque<Element> stack = new ArrayDeque<>();
        stack.push(new Element(root, -1));
        c2 = root.cost;
        int i = 0;
        while(i>=0) {
            ParentNode tmpNode = stack.peek().node.children.get(i);
            while (tmpNode.children.size() > 0) {
                stack.push(new Element(tmpNode, tmpNode.cost));
                tmpNode = tmpNode.children.get(i);
                c2 += tmpNode.cost;
            }
            c1 = Math.min(c1, c2);
            final Element pop = stack.pop();
            c2 = c2 - pop.node.cost;
            i = pop.pos+1;
        }
        return c1;
    }
}

class Element {
    ParentNode node;
    int pos;

    public Element(ParentNode root, int cost) {
    }
}

class ParentNode {
    List<ParentNode> children = new ArrayList<>();
    int cost;
}

class DocumentNode extends  ParentNode{

}

class XMLNode extends ParentNode {

}