package practice;

import java.util.LinkedList;
import java.util.Queue;

public class maximum_level_order_sum {

	static int maxLevelSum(TreeNode root) {
		
		if(root.left == null && root.right == null) return 1;
		
		//Queue for BFS traversals
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root); // adding root node to start with
		int maxSum = Integer.MIN_VALUE;
		int level = 1; // root node level is 1, and its childrens will be 2 and so on, this variable will keep incrementing the level as we go down
		int ANS = 0; //the final answer 
		int tempSum = 0; //the temporary variable for summing the current level
		
		while (!q.isEmpty()) {
			
			int size = q.size();

			//Unlike traditional bfs we will iterate through each level at once (in one loop) and do their sum,
			//plus adding their childs into the queue
			
			//This will keep only elements that are present at any level and not from above or below levels
			//And this will help to sum all elements
			for(int i = 0; i < size; i++) {
				
				TreeNode node = q.poll();
				if(node.left != null) q.add(node.left);
				if(node.right != null) q.add(node.right);
				
				tempSum += node.val;
				
			}
			
			//If any level sum is greater than max then we update the maxsum and update our ans variable
			//here we only check tempSum > maxSum and not tempSum >= maxSum because we want to return the topmost level
			//so if any tree has levels with same sum then we have to return the topmost level
			if(tempSum > maxSum) {
				maxSum = tempSum;
				ANS = level;
			}
			level++;	
			
		}
		return ANS;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(-100 > -500);
	}

}
