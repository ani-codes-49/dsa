package practice;

public class symmetric_tree {

	static boolean helper(TreeNode left, TreeNode right) {
		
		if(left == null && right == null) return true; // base case, if we reach here then till this point tree is symmetrical
		
		if((left != null && right == null) || right == null && left != null) return false; //condition which violates the symmetric condition
		
		if(left != null && right != null) {
			if(left.val != right.val) return false; //condition which violates symmetric tree condition
		}
		
		//we'll traverse the tree from outside to inside
		boolean l = helper(left.left, right.right);
		if(!l) return false; //if false then its the answer
		boolean r = helper(left.right, right.left);
		return r;
		
	}
	
	static boolean isSymmetric(TreeNode root) {
		
		if(root.left == null && root.right == null) return true;
		return helper(root.left, root.right);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
