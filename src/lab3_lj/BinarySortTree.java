package lab3_lj;

/**
 * The class creates a random binary sort tree with 1023 nodes and computes and
 * output the average depth of all the leaves in the tree and the maximum depth
 * of all the leaves.
 * 
 * @author fujita
 *
 */

public class BinarySortTree {

	/**
	 * An object of type TreeNode represents one node in a binary tree of strings.
	 */
	private static class TreeNode {
		double item; // The data in this node.
		TreeNode left; // Pointer to left subtree.
		TreeNode right; // Pointer to right subtree.

		TreeNode(double x) {
			// Constructor. Make a node containing x.
			// Note that left and right pointers are null.
			item = x;
		}
	} // end class TreeNode

	private static TreeNode root; // Pointer to the root node in a binary tree.
									// This tree is used in this program as a
									// binary sort tree. When the tree is empty,
									// root is null (as it is initially).

	/**
	 * Add the item to the binary sort tree to which the global variable "root"
	 * refers. (Note that root can't be passed as a parameter to this routine
	 * because the value of root might change, and a change in the value of a formal
	 * parameter does not change the actual parameter.)
	 *
	 * @param newItem
	 */
	private static void treeInsert(double newItem) {
		if (root == null) {
			// The tree is empty. Set root to point to a new node containing
			// the new item. This becomes the only node in the tree.
			root = new TreeNode(newItem);
			return;
		}
		TreeNode runner; // Runs down the tree to find a place for newItem.
		runner = root; // Start at the root.
		while (true) {
			if (newItem < runner.item) {
				// Since the new item is less than the item in runner,
				// it belongs in the left subtree of runner. If there
				// is an open space at runner.left, add a new node there.
				// Otherwise, advance runner down one level to the left.
				if (runner.left == null) {
					runner.left = new TreeNode(newItem);
					return; // New item has been added to the tree.
				} else
					runner = runner.left;
			} else {
				// Since the new item is greater than or equal to the item in
				// runner, it belongs in the right subtree of runner. If there
				// is an open space at runner.right, add a new node there.
				// Otherwise, advance runner down one level to the right.
				if (runner.right == null) {
					runner.right = new TreeNode(newItem);
					return; // New item has been added to the tree.
				} else
					runner = runner.right;
			}
		} // end while
	} // end treeInsert()

	/**
	 * @param node
	 * @return count of the leaves
	 */
	private static int countLeaves(TreeNode node) {
		if (node == null) { // tree is empty, so it certainly doesn't contain item
			return 0;
		} else if (node.right == null && node.left == null) {
			// node is a leaf
			return 1;
		} else {
			// node is not a leaf, so method call itself to count in the subtrees
			return countLeaves(node.right) + countLeaves(node.left);
		}
	} // end countLeaves()

	/**
	 * If given method is not a leaf the depth is increased and call itself again.
	 * 
	 * @param node
	 * @param depth
	 * @return sum of the depth
	 */
	private static int sumOfDepths(TreeNode node, int depth) {
		if (node == null) { // Tree is empty, so it certainly doesn't contain item.
			return 0;
		} else if (node.right == null && node.left == null) {
			// node is a leaf and the bottom of the tree so return depth.
			return depth;
		} else {
			// node is not a leaf, so method call itself to compute in the subtrees
			return sumOfDepths(node.right, depth++) + sumOfDepths(node.left, depth++);
		}
	} // end sumOfDepths()

	/**
	 * If given method is not a leaf the depth is increased and call itself again.
	 * 
	 * @param node
	 * @param depth
	 * @return maximum of the depth
	 */
	private static int maximumDepth(TreeNode node, int depth) {
		if (node == null) { // Tree is empty, so it certainly doesn't contain item.
			return 0;
		} else if (node.right == null && node.left == null) {
			// node is a leaf and the bottom of the tree so return depth.
			return depth;
		} else {
			// node is not a leaf, so method call itself and count both right and left
			// subtrees and compare with them.
			int rightMax = maximumDepth(node.right, depth++);
			int leftMax = maximumDepth(node.left, depth++);

			if (rightMax > leftMax) {
				return rightMax;
			} else {
				return leftMax;
			}
		}

	}// end maximumDepth()

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create random real number and call treeInsert() to create binary sort tree
		for (int i = 0; i < 1023; i++) {
			treeInsert(Math.random());
		}

		double averageDepth = (double) sumOfDepths(root, 0) / countLeaves(root);

		// print the result
		System.out.println("Count of the leaves: " + countLeaves(root));
		System.out.println("Sum of the depths: " + sumOfDepths(root, 0));
		System.out.println(String.format("The average of depths:%.3f", averageDepth));
		System.out.println("The maximum depths: " + maximumDepth(root, 0));
	}
}