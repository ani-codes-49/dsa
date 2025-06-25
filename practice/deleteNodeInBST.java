package practice;

import java.util.ArrayList;
import java.util.List;

public class deleteNodeInBST {
	
	static boolean isDeleted = false; /// we can avoid calling for left or right subtrees if we've already deleted any node in the bst
									  /// that will save time
	///we can either append left subtree to the right subtree's leftest side or append right subtree to the left subtree's rightest side

//	appending right subtree to left subtree
	private static TreeNode helper(TreeNode left, TreeNode right, int key) {

		if(left == null && right == null) return null;
		if(left == null) return right;
		if(right == null) return left; ///edge and base cases
		
		TreeNode mover = left; ///we will find the rightmost part of the left subtree and append the right subtree to the right of it
							   /// as the right side subtree will definitely be bigger than the rightmost node of the left subtree (as its a bst)
		while (mover != null && mover.right != null) mover = mover.right;
		mover.right = right; /// appending the root.right = given right subtree
		return left; ///this left will be our new head as we're appending right tree to the rightmost.right of the left subtree
	}
	
	//appending left subtree to the right subtree
//	private static TreeNode helper(TreeNode left, TreeNode right, int key) {
//
//		if(left == null && right == null) return null;
//		if(left == null) return right;
//		if(right == null) return left; ///edge and base cases
//		
//		TreeNode mover = right; ///we will find the rightmost part of the left subtree and append the right subtree to the right of it
//							   /// as the right side subtree will definitely be bigger than the rightmost node of the left subtree (as its a bst)
//		while (mover != null && mover.left != null) mover = mover.left;
//		mover.left = left; /// appending the root.right = given right subtree
//		return right; ///this left will be our new head as we're appending right tree to the rightmost.right of the left subtree
//	}
	
	/// the idea is to search the node in the bst in o(log n) time
	/// then send the found node's left and right childs to the helper function which will give us the new root
	/// then we will return the new root which we got
	/// the new root will be assigned to the left/ right subtree of a node from which it was called
	
	private static TreeNode helper(TreeNode root, int key) {

		if (root == null) ///base case
			return null;

		if (root.val == key) { /// if we find the node with the given key then we will call our helper method and pass left and right childs to it
							   ///which will eventually give us the new root (with appending right subtree to the end of left subtree)
			root = helper(root.left, root.right, root.val);
			return root; /// we will return the root which we got 
		}

		///binary search logic
		if (key <= root.val && !isDeleted)
			root.left = helper(root.left, key); /// the root which we returned earlier will be attached to the left subtree if found
		if (key >= root.val && !isDeleted)
			root.right = helper(root.right, key);/// the root which we returned earlier will be attached to the right subtree if found
		
		return root;
	}

	static TreeNode deleteNode(TreeNode root, int key) {

		if (root == null) ///base case
			return null;

		if (root.left == null && root.right == null && root.val == key) ///if its the only node in the bst and the key is also the same
																		///we return null as its the only node present in the bst
			return null;
		
		if(root.val == key) { ///this is the special case that if we have to delete the root thats given to us
		
			///we will go from root.left -->> root.right != null 
			///then attach root.right.right = right node of the original root (the right side of the given bst)
			///then return the root.left as the new node as its our new head
			
			///if the left tree is null then we have to return root.right as we have to delete the current node
			if(root.left == null) return root.right;
			
			TreeNode left = root.left; //store the left node (new head)
			TreeNode mover = left;//keep mover variable so that we don't loose access to the original left pointer (new head)
			
			while (mover.right != null) mover = mover.right; // go till right
			mover.right = root.right; //attach right.right = right subtree of the original bst
			return left; //return the new head
		}

		///else we will implement our logic
		helper(root, key);
		return root;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
