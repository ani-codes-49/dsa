package practice;

public class invert_binary_tree {

	static TreeNode invert(TreeNode root) {
		
		if(root == null) return root;
		
		// general swapping logic for swapping left node with right
		// only root can modify its child (left, right) nodes hence we cannot be at a specific node and swap them 
		
		//This is not correct
		// rightChild = leftChild;
		// leftChild = null; 
		
		//This is correct
		//root.rightChild = root.leftChild;
		//root.leftChild = null;
		
		//However we can still swap values with being at the child node's level
	
		// we have to do that with left and right node's parent
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		
		//normal dfs code
		invert(root.left);
		invert(root.right);
		
		return root;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
