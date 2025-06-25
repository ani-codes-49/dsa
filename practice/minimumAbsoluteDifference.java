package practice;

import java.util.ArrayList;
import java.util.List;

public class minimumAbsoluteDifference {

	static List<Integer> helper(TreeNode root, List<Integer> ans) {

		if (root == null)
			return new ArrayList<>();

		ans = helper(root.left, ans);
		ans.add(root.val);
		ans = helper(root.right, ans);

		return ans;
	}

	/*
	 * TC - o(N)
	 * SC - o(N) space for storing elements (inorder)
	 */
	static int getMinDiff(TreeNode root) {

		List<Integer> inorder = new ArrayList<>();
		helper(root, inorder);
		int minDiff = Integer.MAX_VALUE;

		for (int i = 0; i < inorder.size() - 1; i++)
			minDiff = Math.min(minDiff, Math.abs(inorder.get(i) - inorder.get(i + 1)));

		return minDiff;
	}
	
	static int minDiff = Integer.MAX_VALUE; //min value for storing the final answer
	static TreeNode prev = null; // previous node will hold the recent node value that has been seen
	
	static int getMinDifference(TreeNode root) {
		
		if(root == null) return minDiff; ///base case
		
		getMinDifference(root.left); // call for left subtree
		//we'll operate in inorder fashion (as moving inorder will cause prev variable to hold values in increasing order)
		///prev variable will store values as 1 -> 2 -> 3 non-decreasing

		///If we have seen any previous node up till now then we'll calculate the math.abs(root.val, prev.val) and update the minDiff
		///if it is less than the minDiff
		if(prev != null) minDiff = Math.min(minDiff, Math.abs(root.val - prev.val)); 
		
		///else we'll update the prev node as the current root (we're setting this between left and right subtree calls as we're moving in inorder fashion)
		///so prev will hold values in increasing order

		prev = root;
		getMinDiff(root.right);
		
		return minDiff;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
