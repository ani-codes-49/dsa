package practice;
import java.util.*;

public class depth_of_binary_tree {

	//DFS
	static int helper(TreeNode root, int depth, int curr) {
        if(root == null) return depth;
        curr += 1;
        depth = helper(root.left, depth, curr);
        depth = helper(root.right, depth, curr);
        return Math.max(depth, curr);
    }
	//DFS
	static int maxDepthDFS(TreeNode root) {
		if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        return helper(root, 0, 0);
	}
	
	//BFS
	static int maxDepthBFS(TreeNode root) {
		
		if(root == null) return 0;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int depth = 1;
		
		while (!queue.isEmpty()) {
			
			TreeNode node = queue.poll();
			if(node.left != null || node.right != null) {
				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
				depth++;
			}
		}
		return depth;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(3);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		
		root.right.right = new TreeNode(5);
		
		System.out.println("Depth of the tree is: " + maxDepthBFS(root));
	}

}
