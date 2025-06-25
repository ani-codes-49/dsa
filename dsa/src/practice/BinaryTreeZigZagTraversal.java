package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigZagTraversal {

	static List<List<Integer>> zigzag(TreeNode root) {
		
		List<List<Integer>> ans = new ArrayList<>();
		if(root == null) return ans;
		
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		ans.add(Arrays.asList(root.val));
		int direction = 1; //0 = L to R || 1 = R to L
		
		while (!q.isEmpty()) {
			
			int size = q.size();
			List<Integer> iterator = new ArrayList<>();
			while (size-- > 0) {
				TreeNode temp = q.poll();
				if(temp.left != null) {
					q.add(temp.left);
					iterator.add(temp.left.val);
				}
				if(temp.right != null) {
					q.add(temp.right);
					iterator.add(temp.right.val);
				}
			}
			if(!iterator.isEmpty()) {
				List<Integer> level = new ArrayList<>();
				if(direction == 0) {
					for(int i = 0; i < iterator.size(); i++) level.add(iterator.get(i));
					ans.add(level);
					direction = 1;
				} else {
					for(int i = iterator.size() - 1; i >= 0; i--) level.add(iterator.get(i));
					ans.add(level);
					direction = 0;
				}
			}
			
			
		}
		return ans;
	}

	static List<List<Integer>> zigzag1(TreeNode root) {
		
		List<List<Integer>> ans = new ArrayList<>();
		if(root == null) return ans;
		
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		int direction = 0; // 0 ==== L -> R || 1 === R -> L
		
		while (!q.isEmpty()) {
			
			int size = q.size(); //for iterating over current level in a loop 
			int[] level = new int[size]; // this will be the current level for a tree
			
			for(int i = 0; i < size; i++) { //Iterating the current level in a tree for adding it in the level array,
											//which will be then added into the final answer
				
				TreeNode temp = q.poll();
				
				//adding the further level nodes in a queue
				if(temp.left != null) q.add(temp.left);
				if(temp.right != null) q.add(temp.right); 
				
				//getting proper index for setting integer in the current level list
				
				//if the direction is 0 then we'll just simply start adding elements in the level array according to i value
				//because direction = 0 means we're going from left to right and normal for loop will increment the i's value and we'll go 
				//left to right (e.g 0 --> 1 --> 2 --> 3 and so on)
				
				//if the direction is 1 then we have to go from right left
				//so first we will reduce size to size - 1 (0 based indexing logic) and then we'll subtract the i's value from the size 
				//so we'll get the proper position from the end as we're subtracting value from the (size - 1) 
				
				//for eg - 
				// level = [_, _, _, _, _]; size = 5, index = 0 to 4
				// if our i = 0 and direction = 1 then we've to enter the values from the end
				// so we'll do (size - 1) = 4 = (4 - i(0)) = 4 
				// 4 is our last position and we want to insert from the last so we got the correct index;
				
				//similarly if i = 3, (size - 1) = 4 = (4 - i(3)) = 1
				//1 will be fourth element from the last if we start adding from the last.
				int index = direction == 0 ? i : (size - 1 - i); 
				
				//setting integer in the level list (to be added in the answer list
				level[index] =  temp.val;
				
				
			}
			//after we complete the current level we reset the flag for the next level
			direction = direction == 0 ? 1 : 0;
			List<Integer> helper = new ArrayList<>();
			for(int i : level) helper.add(i);
			ans.add(helper); // faster way for adding the current level into the final answer	
//			ans.add(Arrays.stream(level).boxed().toList()); //too slow
		}
		return ans;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

}
