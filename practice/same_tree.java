package practice;

public class same_tree {

	static boolean sameTree(TreeNode p, TreeNode q) {

		if (p == null && q == null)
			return true; // base case, and if we reach here then the both trees until this path are same,
						// as we applied conditions for checking violations
		
		else if ((p != null && q == null) || (p == null && q != null)) //this condition violates the rules of both trees to be same
			return false;
		else if (p.val != q.val) // this condition violates too
			return false;

		boolean left = sameTree(p.left, q.left); //call for left subtrees of both the trees
		if (!left) // if any left subtree has violated the condition then doesn't need to go to right subtree, return false instead
			return false;
		boolean right = sameTree(p.right, q.right); // call for right subtrees of both the trees
		return right;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
