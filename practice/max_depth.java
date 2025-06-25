package practice;

public class max_depth {

	static int helper(TreeNode root, int depth, int maxDepth) {
		if(root == null) return maxDepth; //return maxDepht (ans) at base condition
		
		maxDepth = helper(root.left, depth + 1, maxDepth); // passing depth + 1 to the next level as we go deeper
		maxDepth = helper(root.right, depth + 1, maxDepth);
		
		return Math.max(maxDepth, depth); //returning the max value while backtracking from each node
	}
	
	static int maxDepth(TreeNode root) {
		
		if(root == null) return 0;
		
		return helper(root, 1, 0); //passing 1 to the depth as if we're at root node we're at level 1
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
