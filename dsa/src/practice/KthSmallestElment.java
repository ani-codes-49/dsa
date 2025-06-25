package practice;

public class KthSmallestElment {
	
	static int smallestElement = -1; /// this will be the final answer
	static int count = -1; ///this count will be incremented from the leftmost node as the bst is already sorted and 
						  ///we can operate with inorder fashion thats why we'll increment count from the leftmost node
						  ///and when our count will become 0 then we'll return the root.val as if we do inorder traversal then
						  ///we're moving in non-decreasing order and when our count will become 0 then we'll be at the kth smallest node that we want

	static int helper(TreeNode root) {
		
		if(root == null) return 0;
		
		///call for left subtree
		helper(root.left);
		if(smallestElement != -1) return smallestElement; ///if we've already found the element then in our smallestElement variable
														  ///there will be a value stored and that's our answer so we'll return that.
		count--;	///after coming back from the left element we'll increment the count (according to the inorder logic)
		if(count == 0) smallestElement = root.val; ///if our count == 0 then we're at the kth smallest node so we'll store it in the global var
		helper(root.right); /// call for right subtree
		
		if(smallestElement != -1) return smallestElement; ///if our global variable (smallestElement) has any answer stored then we'll return it

		return root.val; /// this is the value we've to return (but however we're not using this value anyways)
	}
	
	static int kthSmallest(TreeNode root, int k) {
		
		if(root == null) {
//			System.gc(); // will cleanup memory but will take more time
			return 0;
		}
			
		count = k;
		helper(root);
//		System.gc(); // will cleanup memory but will take more time
		return smallestElement;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
