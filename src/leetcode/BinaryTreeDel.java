package leetcode;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeDel<T extends Comparable<T>> {

	public static class Node<T> {
		T data;
		Node<T> left, right;
		public Node(T data) {
			this.data = data;
		}
		@Override
		public String toString() {
			String s = String.format("Root = %s, L= %s, R= %s", 
					data == null? "NULL" : data.toString(), 
							left == null ? "NULL" : left.data, 
									right == null ? "NULL" : right.data).toString();
			return s;
		}
	}

	private Node<T> root;
	/************** PRIVATE HELPER METHODS ************************/
	private void insertNode(T data, Node<T> node){
		if (data.compareTo(node.data) < 0) {
			if (node.left == null) {
				node.left = new Node<>(data);
			} else
				insertNode(data, node.left);
		} else {
			if (node.right == null) {
				node.right = new Node<>(data);
			} else
				insertNode(data, node.right);
		}
	}

	private T getMin(Node<T> node) {
		if (node.left != null)
			return getMin(node.left);
		else
			return node.data;
	}


	private T getMax(Node<T> node) {
		if (node.right != null)
			return getMax(node.right);
		else
			return node.data;
	}
	
    private void dfs(Node<T> node) {
    	/* inorder */
    	if (node == null) return;
    	dfs(node.left);
    	System.out.println(node);
    	System.out.println(node.right);
    }
    
    private boolean sameTree(Node<T> tree1 , Node<T> tree2) {
    	if (tree1 == null && tree2 == null ) return true;
    	if (tree1 == null && tree2 != null) return false;
    	if (tree1 != null && tree2 == null) return false;
    	
    	if (sameTree(tree1.left, tree2.left) && sameTree(tree1.right, tree2.right)) {
    		if (tree1.data.compareTo(tree2.data) == 0)
    			return true;
    	} 
    	return false;
    }
    
    private Node<T> findNode(T data, Node<T> node) {
		if (node == null) return null;
		if (data.compareTo(node.data) == 0)
			return node;
		if (data.compareTo(node.data) < 0){
			return findNode(data, node.left);
		} else
			return findNode(data, node.right);	
	}
    
	private static<U> Node<U> createBSTFromSortedArray(U[] arr, int start, int end) {
		if (start > end) return null;
		int mid = (start + end)/2;
		Node<U> head = new Node<> (arr[mid]);
		head.left = createBSTFromSortedArray(arr, start, mid - 1);
		head.right =  createBSTFromSortedArray(arr, mid+1, end);
		return head;
		
	}
	
	private boolean hasPathSum(Integer sum, Node<Integer> node) {
		if (node == null) return false;
		if (node.left == null && node.right == null) {
			return node.data == sum;
		}
		int remSum = sum - node.data;
		boolean hasLeftSum = node.left == null ? false : hasPathSum(remSum, node.left);
		if (hasLeftSum == true) return true;
		boolean hasRightSum = node.right == null ? false : hasPathSum(remSum, node.right);
		if (hasRightSum == true) return true;
		return false;
	}
	
	private Node<T> LCA(Node<T> node, Node<T> a, Node<T> b) {
		if (node == null) return null;
		if (node == a || node == b) return node;
		
		Node<T> leftLCA = LCA(node.left, a, b);
		Node<T> rightLCA = LCA(node.right, a, b);	
		
		if (leftLCA != null && rightLCA != null)
			return node;
		else if (leftLCA!= null) {
			return node.left;
		} else
			return node.right;
	}

	private Node<T> delete(Node<T> node, T a) {
		if (node == null) return null;
		if (node.data.compareTo(a) > 0) {
			node.left = delete(node.left, a);
		} else if (node.data.compareTo(a) < 0) {
			node.right = delete(node.right, a);
		} else {
			if (node.left == null && node.right == null){
				System.out.println("Removing leaf element");
				return null;
			}
			
			if (node.left != null && node.right == null) {
				Node<T> tmp = node.left;
				System.out.println("Removing element with one left element");
			    node = null;
			    return tmp;
			}
			
			if (node.right != null && node.left == null) {
				Node<T> tmp = node.right;
				System.out.println("Removing element with one Right element");
			    node = null;
			    return tmp;
			}
			
			System.out.println("Removing element with two children");
			Node<T> predecessor = getPredecessor(node.left);
			node.data = predecessor.data; //swap the data
			//delete the node with the data from left tree
			node.left = delete(node.left, predecessor.data);
		}
		return node;
	}
    private Node<T> getPredecessor(Node<T> node) {
		if (node.right != null)
			return getPredecessor(node.right);
		return node;
	}

	/************* PUBLIC METHODS **************************/
	public void insert(T data) {
		if (root == null) {
			root = new Node<>(data);
		} else
			insertNode(data, root);
	}

	public T getMin(){
		if (root == null) return null;
		return getMin(root);
	}

	public T getMax(){
		if (root == null) return null;
		return getMax(root);
	}

    public void bfs() {
    	Queue<Node<T>> q = new LinkedList<>();
    	q.add(root);
    	while (q.isEmpty() == false) {
    		Node<T> curr = q.remove();
    		System.out.println(curr);
    		if (curr.left != null)
    			q.add(curr.left);
    		if (curr.right != null)
    			q.add(curr.right);
    	}
    }
    
    public void dfs() {
    	/* inorder => ascending*/
    	dfs(root);
    }
    
    public Node<T> findNode(T data){
    	return findNode(data, root);
    }

	public boolean sameTree(BinaryTreeDel<T> t) {
    	return sameTree(root, t.root);
    }
	
	public static<U> Node<U> createBSTFromSortedArray(U[] arr){
		int start = 0;
		int end = arr.length - 1;
		return createBSTFromSortedArray(arr,0, end);
		
	}

	public boolean hasPathSum(Integer data){
		if (root.data.getClass().isAssignableFrom(Integer.class)){
			return hasPathSum(data, (Node<Integer>)root);
		} else
			throw new ClassFormatError(root.data.getClass() + " can't have sum");
	}

	public Node<T> LCA(Node<T> a, Node<T> b) {
		return LCA(root, a, b);
	}

	public Node<T> delete(T a) {
		return delete(root, a);
	}
	
	
	/********** MAIN **************************/
	public static void main(String[] args) {
		BinaryTreeDel<Integer> bst = new BinaryTreeDel<>();
		bst.insert(10);
		bst.insert(-1);
		bst.insert(1);
		bst.insert(0);
		bst.insert(1000);
		bst.insert(-22);
		System.out.println(bst.getMin());
		System.out.println(bst.getMax());
		bst.bfs();
		System.out.println("=============");
		bst.dfs();
		
		BinaryTreeDel<Integer> bst1 = new BinaryTreeDel<>();
		bst1.insert(10);
		bst1.insert(-1);
		bst1.insert(1000);
		bst1.insert(1);
		bst1.insert(0);
		bst1.insert(-22);
		
		System.out.println(bst.sameTree(bst1)); //true
		System.out.println(bst.findNode(1000));
		
		Integer[] arr = {1, 2, 3, 4, 5};
		BinaryTreeDel<Integer> newBst = new BinaryTreeDel<>();
		newBst.root = createBSTFromSortedArray(arr);
		newBst.bfs();
		
		System.out.println(newBst.hasPathSum(6));
	}

}
