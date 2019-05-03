package leetcode;

import java.util.Queue;
import java.util.LinkedList;
import java.util.SortedMap;


// BST can get skewed and in that case it cause search to O(n)
// Read about AVL trees - selfing balancing BST
// Read about Red black tree - also selfing balancing BST (rotation is less restrictive)
// E.g. of Red Black tree -TreeMap<K,V> and TreeSet<E>
public class BinaryTree<T extends Comparable<T>> {
	// https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/trees.html

	//private Node<T> head;
	public BinaryTree() {
		//this.head = null;
	}

	// insert as a BST
	public Node<T> insertInBST(Node<T> head, Node<T> node){
		if (head == null) 
			return node;
		if ( node.getData().compareTo(head.getLeftChild().getData()) > 0 ) 
			head.setLeftChild(insertInBST(head.getRightChild(), node));
		else 
			head.setRightChild(insertInBST(head.getLeftChild(), node));
		return head;
	}

	//bfs
	public void bfs(Node<T> head){
		//Add the head to queue
		//Loop: while queue is not empty
		//      remove current element from queue and print
		//      	getLeft child add to queue
		//    		getRight child add to queue 
		Queue<Node<T>> queue = new LinkedList<>();
		queue.add(head);

		while(!queue.isEmpty()) {
			Node<T> currNode = queue.remove();
			print(currNode);
			//for (Node<T> itr : currNode.getChildren())
			if (currNode.getLeftChild()!=null)
				queue.add(currNode.getLeftChild());
			if (currNode.getRightChild()!=null)
				queue.add(currNode.getRightChild());		
		}
	}
    //dfs
	public void dfs(Node<T> head){
		//preorder
		print(head);
		dfs(head.getLeftChild());
		dfs(head.getRightChild());
		//inorder - gives assending order for a BST
	}
	// sameTree
	public boolean sameTree(Node<T> tree1, Node<T> tree2){
		// if both trees are null => return true
		// if only one tree is null => return false

		// Call sameTree recursively for tree1.getLeftChild(), tree2.getLeftChild()

		// Finally we will have leaf nodes, and we compare their data

		if (tree1 == null && tree2 == null) return true;
		if (tree1 == null || tree2 == null) return false;

		if (sameTree(tree1.getLeftChild(),tree1.getLeftChild()) && sameTree(tree1.getRightChild(),tree1.getRightChild())) {
			if (tree1.data.compareTo(tree2.data) == 0)
				return true;
			else 
				return false;
		}
		return false;
	}

	// look up in a BST
	public Node<Integer> lookup (Node<Integer> head, Integer data){
		// if head == null, return null
		if (head == null) return null;
		if (head.getData().compareTo(data) == 0)
			return head;
		else if (data.compareTo(head.getData()) < 0){
			return lookup(head.getLeftChild(), data);
		} else
			return lookup(head.getRightChild(), data);
	}

	// 	min value in a BST
	public  int minVal(Node<Integer> head){
		if (head == null)
			return Integer.MIN_VALUE;
		if (head.getLeftChild() == null)
			return head.getData();
		return minVal(head.getLeftChild());
	}

	// 	max value in a BST
	public  int maxVal(Node<Integer> head){
		if (head == null)
			return Integer.MAX_VALUE;
		if (head.getRightChild() == null)
			return head.getData();
		return minVal(head.getRightChild());
	}

	// max depth
	public static int maxDepth(Node<Integer> head){
		if (head == null)
			return 0;
		if (head.getLeftChild()== null && head.getRightChild() == null) 
			return 1;
		int leftDepth = 1 + maxDepth(head.getLeftChild());
		int rightDepth = 1+  maxDepth(head.getRightChild());
		return Math.max(leftDepth, rightDepth );
	}

	// mirror
	public static void mirror(Node<Integer> head){
		if (head == null)
			return ;
		mirror(head.getLeftChild());
		mirror(head.getRightChild());

		Node<Integer> temp = head.getLeftChild();
		head.setLeftChild(head.getLeftChild());
		head.setRightChild(temp);
	}

	// delete
	public static Node<Integer> delete(Node<Integer> node, Integer data){
		if( node == null ) return node;
		
		if (data < node.getData()){
			node.setLeftChild(delete(node.getLeftChild(), data));
		} else if (data > node.getData()) {
			node.setRightChild(delete(node.getRightChild(), data));		
		} else {
			
			// we have found the node we want to remove !!!
			if( node.getLeftChild() == null && node.getRightChild() == null ) {
				System.out.println("Removing a leaf node...");
				return null;
			}
			
			if( node.getLeftChild() == null ) {
				System.out.println("Removing the right child...");
				Node<Integer> tempNode = node.getRightChild();
				node = null;
				return tempNode;
			} else if( node.getRightChild() == null ) {
				System.out.println("Removing the left child...");
				Node<Integer> tempNode = node.getLeftChild();
				node = null;
				return tempNode;
			}
			
			// this is the node with two children case !!!
			System.out.println("Removing item with two children...");
			Node<Integer> tempNode = getPredecessor(node.getLeftChild());
			
			node.setData(tempNode.getData());
			node.setLeftChild( delete(node.getLeftChild(), tempNode.getData()) );
		}
		
		return node;
		
	}
	private static Node<Integer> getPredecessor(Node<Integer> node) {
		if (node.getRightChild() != null){
			return getPredecessor(node.getRightChild());
		}
		return node;
	}
	
	   //LCA for generic binary tree (for BST it is simpler)
    public static Node<Integer> LCA(Node<Integer> root, Node<Integer> a, Node<Integer> b ){
		if (root == null) 
			return root;
		if (root == a || root == b)
			return root;
		Node<Integer> leftLCA = LCA(root.getLeftChild(), a, b);
		Node<Integer> RightLCA = LCA(root.getRightChild(), a, b);
		if (leftLCA != null && RightLCA != null)
			return root;
		if (leftLCA != null)
			return root.getLeftChild();
		else
			return root.getRightChild();
		
		// If BST
		// if (root > Max(a,b) return LCA(root.left, a, b)
		// if (root < Min(a,b) return LCA(root.left, a, b)
		// else return root
    	
    }
    
    // hasSum
    public static boolean hasSum (Node<Integer> root,  int sum){
    	if (root.getLeftChild() == null && root.getRightChild() == null)
    		return sum == root.getData();
    	int subsum = sum - root.getData();
    	if (root.getLeftChild() != null){
    		boolean hasSum = hasSum(root.getLeftChild(), subsum);
    		if (hasSum == true)
    			return true;
    	}
    	if (root.getRightChild() != null){
    		boolean hasSum = hasSum(root.getRightChild(), subsum);
    		if (hasSum == true)
    			return true;
    	}  
    	return false;
    }

	 //Create a balanced Binary Search Tree (BST) from a sorted array
	 public static Node<Integer> createBST(int[] arr) {
			int start = 0;
			int end = arr.length - 1;
			return createBST(arr, 0, end);
			
	 }
	 
	 private static Node<Integer> createBST(int[] arr, int start, int end) {
			if (start > end) return null;
			int mid = (start+end) / 2;
			Node<Integer> root = new Node<>(arr[mid]);
			root.setLeftChild(createBST(arr, start, mid - 1));
			root.setRightChild(createBST(arr, mid + 1, end));
			return root;
	 }
	 
	private void print(Node<T> node) {
		System.out.print(node.getData() + " -> ");
	}


	public static class Node<T> {
		private T data;
		private Node<T> leftChild;
		private Node<T> rightChild;

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node<T> leftChild) {
			this.leftChild = leftChild;
		}

		public Node<T> getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node<T> rightChild) {
			this.rightChild = rightChild;
		}

		public Node() {
			this.data = null;
			this.leftChild = null;
			this.rightChild = null;
		}

		public Node(T data) {
			this.data  = data;
		}

	}

}
