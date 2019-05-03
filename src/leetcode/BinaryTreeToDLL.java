package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeToDLL {
	
	private static Node head;
	private static Node prev;
	
	public static class Node {
		public Node(int i) {
			data = i;
		}
		public Node() {
		}
		Integer data;
		Node l, r;
		@Override
		public String toString() {
			return String.format("Data=%d, L=%s, R=%s", data, l == null ? "NULL ": l.data, r == null ? "NULL" : r.data);
			
		}
	}
	// Diameter of Binary Tree = Max ( Max(lDiameter, RDiameter), 1+lHeight+rHeight)
	// Print all elemts from root to leaf - https://www.youtube.com/watch?v=zIkDfgFAg60
	// printRootToLeaf(Node root) {
	//    if root == null return
	//    stack.push(root);
	//    printRootToLeaf(root.left)
	//    if isLeafNode(root) printStack();
	//    printRootToLeaf(root.right)
	//    stack.pop();
	//https://www.youtube.com/watch?v=aYwiLCCdb-k
	private static void  convertInorderBased(Node node) { //inplace
		if (node == null) return;
		convertInorderBased(node.l);
		if (prev == null) head = node;
		else {
			prev.r = node;
			node.l = prev;
		}
		prev = node;
		convertInorderBased(node.r);
	}
	
	public static void spiralPrint(Node node){
	/*	         1
		    2          3
		7      6    5      4
		
		1,2,3,4,5,6,7 */
		
	/*	| |  |2|
		|1|  |3|
		L->R  R->L */
		
		Deque<Node> s1 = new LinkedList<>(); // You can use Stack as-well but that uses Vectors
		Deque<Node> s2 = new LinkedList<>(); // Though dequeue implements queue interface, it can be used as stack by using pop and push instead of addFirst
		s1.push(node);
		System.out.println();
		while(s1.isEmpty() == false || s2.isEmpty() == false){
			Node curr;
			while (!s1.isEmpty()){
				curr = s1.pop();
				System.out.println(curr.data+", ");
				if (curr.r!= null) s2.push(curr.r);
				if (curr.l!= null) s2.push(curr.l);
			}
			
			while (!s2.isEmpty()){
				curr = s2.pop();
				System.out.println(curr.data+", ");
				if (curr.l!= null) s1.push(curr.l);
				if (curr.r!= null) s1.push(curr.r);
			}
		}
	
		
		
	}
	
	public static void main(String[] args) {
		Node tree = null;;
		Node ten = new Node(10);
		Node twelve = new Node(12);
		Node fifteen = new Node(15);
		Node twentyFive = new Node(25);
		Node thirty = new Node(30);
		Node thirtysix = new Node(36);
		tree = ten;
		ten.l = twelve;
		ten.r = fifteen;
		twelve.l = twentyFive;
		twelve.r = thirty;
		fifteen.l = thirtysix;
		//System.out.println(tree.toString());
		bfs(tree);
		convertInorderBased(tree);
		//convertBFSBased(tree);
		printDLL(head);
		
		
		Node one = new Node(1);
		Node two = new Node(2);
		Node three = new Node(3);
		Node four = new Node(4);
		Node five = new Node(5);
		Node six = new Node(6);
		Node seven = new Node(7);
		one.l = two; one.r = three;
		two.l = seven; two.r = six;
		three.l = five; three.r = four;
		spiralPrint(one);
	}
	
	private static void convertBFSBased(Node tree) {
		Queue<Node> q = new LinkedList<>();
		q.add(tree);
		while(!q.isEmpty()){
			Node curr = q.poll();
			Node newNode = new Node(curr.data);
			if (prev == null) head = newNode;
			else {
				newNode.l = prev;
				prev.r = newNode;
			}
			prev = newNode;
			if (curr.l != null) q.add(curr.l);
			if (curr.r != null) q.add(curr.r);
		}
		
	}

	private static void printDLL(Node head) {
		Node ptr = head;
		while(ptr != null) {
			System.out.print(ptr.data + " -> ");
			ptr = ptr.r;
		}
	}

	private static void bfs(Node tree) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(tree);
		while (!queue.isEmpty()){
			Node curr = queue.poll();
			System.out.println(curr);
			if (curr.l != null) queue.add(curr.l);
			if (curr.r != null) queue.add(curr.r);
		}
		

	}

}
