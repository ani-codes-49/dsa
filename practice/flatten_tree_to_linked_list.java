package practice;

public class flatten_tree_to_linked_list {

	private static TreeNode helper(TreeNode root) {
		if(root == null) return null;
		
		root.left = helper(root.left);
		root.right = helper(root.right);
		if(root.left != null) {
			TreeNode temp = root.right;
			root.right = root.left;
			root.left = null;
			TreeNode mover = root.right;
            while (mover.right != null) mover = mover.right;
            mover.right = temp;
		}
		return root;
	}

	public static void flatten(TreeNode root) {
		
		if(root == null || (root.left == null && root.right == null)) return;
		
		root = helper(root);
		return;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
