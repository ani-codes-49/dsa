package practice;

public class isValidBST {

	static boolean helper(TreeNode root, long start, long end) {

		if (root == null)
			return true;

		if(root.val <= start || root.val >= end) return false; ///if the current root.val is not lying between the given range then we return false;
		
		boolean left = helper(root.left, start, root.val); 
		///we will call the left subtree and pass the range as left(root.left, int.min, root.val)
		///because any left subtree nodes should lie only within this range (according to the bst logic) otherwise its not a valid bst
		///after we pass a int.min and root.val to the left subtree the left subtree elements are forced to lie between the given range
		///which satisfies our bst logic.
		///
		if(!left) return left; ///if we've already found out that our tree isn't a valid bst then we'll return false without calling further trees
		///for the right subtree we'll pass the range as right(root.right, root.val, int.max); as right subtree values should only lie between the
		///given range
		boolean right = helper(root.right, root.val, end);
		///after we pass a root.val and int.max to the right subtree the left subtree elements are forced to lie between the given range
		///which satisfies our bst logic.
		if(!right) return right; ///if we've already found out that our tree isn't a valid bst then we'll return false without calling further trees
		
		return true; ///default case if everything is going nice then we'll return true
	}

	static boolean validateBST(TreeNode root) {

		if (root.left == null && root.right == null)
			return true;
		return helper(root, Long.MIN_VALUE, Long.MAX_VALUE); ///passing range as start and end

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(new TreeNode(1), new TreeNode(3), 2);
		System.out.println(validateBST(root));
	}

}
