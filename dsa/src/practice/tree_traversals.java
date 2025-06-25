package practice;

public class tree_traversals {

	/*
	 * Left --> Root --> Right
	 */
	static void inOrder(TreeNode root) {
		
		if(root == null) return;
		
		inOrder(root.left);
		System.out.print(root.val + " --> ");
		inOrder(root.right);
		
	}
	
	/*
	 * Root --> Left --> Right
	 */
	static void preOrder(TreeNode root) {
		
		if(root == null) return;
		
		System.out.print(root.val + " --> ");
		
		preOrder(root.left);
		preOrder(root.right);
		
	}
	
	/*
	 * Left --> Right --> Root
	 */
	static void postOrder(TreeNode root) {
		
		if(root == null) return;
		
		postOrder(root.left);
		postOrder(root.right);
		
		System.out.print(root.val + " --> ");
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(5);
		root.left = new TreeNode(2);
		
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		/*
		 * 				  1
		 * 			   /    \
		 * 			  2      5
		 * 			 / \    / \
		 * 			3   4  6   7
		 */
		
		
		System.out.println("In-order Traversal: ");
		inOrder(root);
		System.out.println();
		System.out.println();
		System.out.println("Pre-order Traversal: ");
		preOrder(root);
		System.out.println();
		System.out.println();
		System.out.println("Post-order Traversal");
		postOrder(root);
		
	}

}
