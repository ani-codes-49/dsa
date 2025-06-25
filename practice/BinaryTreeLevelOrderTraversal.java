package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
	
	static List<List<Integer>> levelOrder(TreeNode root) {
		
		List<List<Integer>> ans = new ArrayList<>(); 
		///final answer to be returned
		if(root == null) return ans; ///base case
		
		Queue<TreeNode> q = new LinkedList<>();	///queue for storing the levels
		q.add(root);/// we'll add root in the queue to be started
		
		while (!q.isEmpty()) {
			
			int size = q.size(); ///getting size for iterating for each level
			List<Integer> level = new ArrayList<>(); ///temporary list for storing level
			while (size-- > 0) {
				
				TreeNode temp = q.poll(); 
				if(temp.left != null) q.add(temp.left); ///adding further level nodes in the queue for the next iteration
				if(temp.right != null) q.add(temp.right);///adding further level nodes in the queue for the next iteration
				level.add(temp.val); ///adding current node in the queue
			}
			ans.add(level); ///adding the current list into the answer 
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
