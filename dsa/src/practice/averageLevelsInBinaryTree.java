package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class averageLevelsInBinaryTree {
//test
	static List<Double> averageOfLevels(TreeNode root) {
		
		List<Double> ans = new ArrayList<>();
		
		if(root.left == null && root.right == null) {
			ans.add((double) root.val);
			return ans;
		}
		
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		
		while (!q.isEmpty()) {
			
			int size = q.size(); //iterate over a particular level in a single loop
			long sum = 0; //store sum in long as we can go above 2^31 - 1 while summing
			int num = 0; //for number of nodes in a current level
			while (size-- > 0) {
				
				TreeNode temp = q.poll();
				sum += temp.val;
				num++;
				
				if(temp.left != null) q.add(temp.left);
				if(temp.right != null) q.add(temp.right);
				
			}
			ans.add((double) sum / num); //store the current level average in a ans list
		}
		return ans;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
