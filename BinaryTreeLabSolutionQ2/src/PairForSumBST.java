
/**
 * This program prints the inorder traversal of given tree and
 * find if a pair exists which on addition provides the given sum value. 
 */
import java.util.ArrayList;

/**
 * @author nishthapradhan
 *
 */

//A binary tree node
class Node {

	int data;
	Node left, right;

	Node(int d) {
		data = d;
		left = right = null;
	}
}

public class PairForSumBST {

	// Root of BST
	Node root;

	// Constructor
	PairForSumBST() {
		root = null;
	}

	// Inorder traversal of the tree
	void inorder() {
		inorderRect(this.root);
	}

	// Utility function for inorder traversal of the tree
	void inorderRect(Node node) {
		if (node == null)
			return;

		inorderRect(node.left);
		System.out.print(node.data + " --> ");
		inorderRect(node.right);
	}

	// This method mainly calls insertRec()
	public void insert(int key) {
		root = insertKey(root, key);
	}

	// A recursive function to insert a new key in BST
	Node insertKey(Node root, int data) {

		// If the tree is empty, return a new node
		if (root == null) {
			root = new Node(data);
			return root;
		}

		// Otherwise, recur down the tree
		if (data < root.data)
			root.left = insertKey(root.left, data);
		else if (data > root.data)
			root.right = insertKey(root.right, data);

		return root;
	}

	// Method that adds values of given BST into ArrayList
	// and hence returns the ArrayList
	ArrayList<Integer> treeToList(Node node, ArrayList<Integer> list) {
		// Base Case
		if (node == null)
			return list;

		treeToList(node.left, list);
		list.add(node.data);
		treeToList(node.right, list);

		return list;
	}

	// method that checks if there is a pair present
	boolean isPairPresent(Node node, int target) {
		// This list a1 is passed as an argument
		// in treeToList method
		// which is later on filled by the values of BST
		ArrayList<Integer> a1 = new ArrayList<>();

		// a2 list contains all the values of BST
		// returned by treeToList method
		ArrayList<Integer> a2 = treeToList(node, a1);

		int start = 0; // Starting index of a2

		int end = a2.size() - 1; // Ending index of a2

		while (start < end) {

			if (a2.get(start) + a2.get(end) == target) // Target Found
			{
				System.out.println("the pair which creates " + target 
						+ " on addition is ("
						+ a2.get(start) + " , " + a2.get(end) + " )" );
				return true;
			}

			if (a2.get(start) + a2.get(end) > target) // decrements end
			{
				end--;
			}

			if (a2.get(start) + a2.get(end) < target) // increments start
			{
				start++;
			}
		}

		System.out.printf("no such nodes are found which create %d on addition!\n", target);
		return false;
	}

	// Driver function
	public static void main(String[] args) {
		PairForSumBST tree = new PairForSumBST();

		tree.insert(40);
		tree.insert(20);
		tree.insert(60);
		tree.insert(10);
		tree.insert(30);
		tree.insert(50);
		tree.insert(70);

		System.out.println("Inorder Traversal of the tree: ");
		tree.inorder();
		System.out.print("\n\nAnd ");
		tree.isPairPresent(tree.root, 130);
	}
}
