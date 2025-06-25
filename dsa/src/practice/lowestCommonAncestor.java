package practice;

import java.util.ArrayList;

public class lowestCommonAncestor {
	
	static TreeNode lowestCommonAncestor(TreeNode root ,TreeNode p, TreeNode q) {
		
		if(root == null) return null;
		
		if(root.val == p.val || root.val == q.val) return root; //If we find a node equals to one of our required nodes (p or q) we return it
		
		//We perform dfs
		TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
		TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);
		
		//If the node we get from the subtrees has following cases then we return respective nodes
		
		if(leftLCA != null && rightLCA != null) return root; //This is our lca node because it has received not null nodes from both subtrees
															 //hence returning the root node
		
		else if(leftLCA == null && rightLCA != null) return rightLCA; //If we get null from left subtree of current node and not null from right
																	  // we return rightest node (not null)
		
		else if(leftLCA != null && rightLCA == null) return leftLCA; // We're at leaf node hence returning the leaf node
		
		else return null; // both left and right lca's will be null hence returning null (hardcoded) to avoid confusion
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(
				new TreeNode(new TreeNode(6), new TreeNode(new TreeNode(7), new TreeNode(4), 2), 5),
				new TreeNode(new TreeNode(0), new TreeNode(8), 1), 3);
		
		TreeNode p = new TreeNode(5);
		TreeNode q = new TreeNode(1);
		
		System.out.println(lowestCommonAncestor(root, p, q).val);
	}

}
