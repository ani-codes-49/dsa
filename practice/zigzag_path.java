package practice;

public class zigzag_path {

	static int helper(TreeNode root, int currPath, int maxPath, int parent) {
		
		if(root == null) return maxPath;
				
		maxPath = helper(root.left, parent == 0 ? 1 : currPath + 1, maxPath, 0); //If our parent is left and still we're going left then 	
																				//the node where we're going his value will be 1 
																				//as new path will be calculated further from that node.
		
		//If we're going opposite direction from our parent's direction then will increment the path as we have to go zigzag.
		
		maxPath = helper(root.right, parent == 1 ? 1 : currPath + 1, maxPath, 1);//Similarly If our parent is right and still we're going right then 	
																				//the node where we're going his value will be 1 
																				//as new path will be calculated further from that node
		
		return Math.max(maxPath, currPath);
		
	}
	
	static int longestZigZagPath(TreeNode root) {
		
		if(root.left == null && root.right == null) return 0;
		
		int ANS = 0;
		ANS = Math.max(ANS, helper(root.left, 1, 0, 0)); //Calling for both subtrees one by one for left the parent value will be 0
		ANS = Math.max(ANS, helper(root.right, 1, 0, 1));//Calling for both subtrees one by one for right the parent value will be 1
		
		return ANS;
		
	}
	
	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(new TreeNode(1), new TreeNode(1), 1);
		root.left.right = new TreeNode(new TreeNode(1), new TreeNode(1), 1);
		root.left.right.left.right = new TreeNode(1);
		
		System.out.println(longestZigZagPath(root));
	}

}
