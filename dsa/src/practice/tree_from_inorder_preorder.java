package practice;

public class tree_from_inorder_preorder {
	
	//Declaring this variable globally as we need to increment the preOrderIndex as we go further in building the tree
	//We cannot depend on the recursive calls to maintain our variable values
	//as recursive calls will update our variable values up and down
	//thats why we have to preserve value (like call-by-ref)
	
	//static variables share the same memory space across different recursive calls (shared variable that updates and preserves value)
	static int preOrderIndex = 0;
	
	//1 take a node from preorder array and find it in the inorder array.
	
	//2 create a new node with the value as the value from the preorder array with the preOrderIndex
	
	//2 after getting that element pass the left side array of that element to the left subtree.
	//3 similarly pass the right side array of that element to the right subtree.
	// Eventually recursion will return the left and right node to its parent
	
	

	//inStart and inEnd are variables for selecting range in inorder array
	
	static int searchIndex(int[] inorder, int val, int left, int right) {
		for(int i = left; i <= right; i++) 
			if(inorder[i] == val) return i;
		
		return -1;
	}
	
	static TreeNode helper(int[] inorder, int[] preorder, int inStart, int inEnd) {
		if(inStart > inEnd) return null; //Base case. If we're at any element and there are not more left or right values present in inorder
										// then that node's childs are basically null
		TreeNode root = new TreeNode(preorder[preOrderIndex]);
		
		int nodeInorderIndex = searchIndex(inorder, preorder[preOrderIndex], inStart, inEnd);
		preOrderIndex++;
		
		root.left = helper(inorder, preorder, inStart, nodeInorderIndex - 1);
		root.right = helper(inorder, preorder, nodeInorderIndex + 1, inEnd);
		return root;
	}
	
	static TreeNode buildTree(int[] inorder, int[] preorder) {
		
		if(inorder.length == preorder.length && preorder.length == 1) {
			return new TreeNode(preorder[0]);
		}
		
		return helper(inorder, preorder, 0, inorder.length - 1);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
