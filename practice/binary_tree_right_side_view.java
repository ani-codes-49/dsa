package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class binary_tree_right_side_view {

	static List<Integer> rightSideView(TreeNode root) {
		if (root == null)
			return new ArrayList<>();

		List<Integer> ans = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		ans.add(root.val); //root node will always be visible from the right side
		
		
		while (!q.isEmpty()) {
			
			int size = q.size(); //will get how many elements are currently present in the queue
			for (int i = 0; i < size; i++) { // run the loop (size) times (will complete adding the next level and removing this level in this loop)
				TreeNode node = q.poll();
				if (node.right != null) //Will add the nodes from the right side as we will add the front element in the ans,
										//as it is the one with visible from right side
					q.add(node.right);

				if (node.left != null)
					q.add(node.left);
			}
			if (!q.isEmpty()) 
				ans.add(q.peek().val); //At this point we will have whole level present in the queue,
										//without any element from the above or below level like in the traditional level order traversal
										//where we have mixed elements from the both levels
			
										//Hence we'll add front element in the queue as its the visible element from the right side
										//(We've added elements from the right side)

		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(new TreeNode(new TreeNode(new TreeNode(5), null, 4), null, 2), new TreeNode(3), 1);

		System.out.println(rightSideView(root));
	}

}
