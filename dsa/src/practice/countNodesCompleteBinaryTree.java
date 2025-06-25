package practice;

public class countNodesCompleteBinaryTree {

	/*
	 * TC - o(N)
	 * SC - o(H) (h = height)
	 */
	static int count = 0;
	static int count(TreeNode root) {
		
		if(root == null) return 0;
		
		count++;
		
		count(root.left);
		count(root.right);
		return count;
	}
	
	static int getHeightLeft(TreeNode root, int height) {
		if(root == null) return 0;
		return getHeightLeft(root.left, height + 1) == 0 ? height : 0;
	}
	
	static int getHeightRight(TreeNode root, int height) {
		if(root == null) return 0;
		return getHeightRight(root.right, height + 1) == 0 ? height : 0;
	}
	
	static int helper(TreeNode root, int depth) {
		
		if(root == null) return 0;
		
		int leftTreeHeight = getHeightLeft(root, 1);
		int rightTreeHeight = getHeightRight(root, 1);
		
		if(leftTreeHeight == rightTreeHeight) return (int) (Math.pow(2, leftTreeHeight) - 1);
		
		return 1 + helper(root.left, 1) + helper(root.right, 1); 
		
	}
	
	static int countNodes(TreeNode root) {
		
		if(root == null) return 0;
		if(root.left == null && root.right == null) return 1;
		if(root.left != null && root.right == null) return 2;
		
		return helper(root, 1);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
