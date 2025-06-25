package practice;

public class binaryTreeMaxPathSum {
	
	//We'll declare a global variable for updating the maxValue that we'll be shared across the recursive calls 
	//(won't affect by the local recursive stack value)
	
	static int maxPathSum = Integer.MIN_VALUE;
	
	static int helper(TreeNode root) {
		
		if(root == null) return 0; //when we reach a null node we return 0 as base case
		
		int left = Math.max(helper(root.left), 0); // we're setting max(helper(), 0) because if left subtree has a negative maxSum value,
												   //then it'll only decrease the existing maxSum value when we add them both
												  // so we're normalizing it to 0 so that even if we get a negative value we turn it into 0 so that
												  // our maxSum value won't get decreased
		int right = Math.max(helper(root.right), 0);
		
		//after we get the value from right and left subtrees of an individual node
		
		/*
		 * 1. First we add both left and right values with the root.val (left + right + root.val)
		 * 	  this means we check the current path that includes (root + root.left + root.right)
		 * 	  this is the basic subproblem for this problem.
		 */
		
		maxPathSum = Math.max(maxPathSum, (left + right + root.val));
		
		/*
		 * 1. Then we return the value as root.val + max(left, right)
		 * 2. Because we can only be at single path and we cannot include both (left and right) childs of any individual node
		 * 3. Because of this we only take the path which has maximum value as eventually we have to find a max value, so we want to maximize it.
		 * 4. Then we return the max(left, right) with the current root.val because we have to consider the current root we're at in the path as well
		 */
		return Math.max(left, right) + root.val;
		
	}
	
	/*
	 * 
	 * The intuition is -
	 * 
	 * 	Go till the depth of the tree (postorder)
	 * 	Calculate the current tree maxPath by doing (root.val + root.left.val + root.right.val) and update the maxPathSum
	 * 	Then return the maxSum path we can get from the current tree (which consist of only left or right node (max(left, right)) + the root)
	 *  while returning store the result as max(recursivefunc() ,0) because if we get a negative value from any subtree (left/ right),
	 *  then negative value will only decrease the maxSum (when added with maxSum); so if we normalize the negative value with 0 then it won't decrease the 
	 *  maxSum value
	 */
	
	static int maxPath(TreeNode root) {
		
		if(root == null) return 0;
		if(root.left == null && root.right == null) return root.val;
		
		helper(root);
		return maxPathSum;
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
