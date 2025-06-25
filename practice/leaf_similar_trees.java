package practice;

import java.util.ArrayList;

public class leaf_similar_trees {

	static ArrayList<Integer> getLeafs(TreeNode root, ArrayList<Integer> list) {
		if (root.left == null && root.right == null) {
			int temp = root.val;
			list.add(temp);
			return list;
		}

		list = getLeafs(root.left, list);
		list = getLeafs(root.right, list);

		return list;

	}

	static boolean leafSimilar(TreeNode root1, TreeNode root2) {

		if ((root1.left == null && root1.right == null) && (root2.left == null && root2.right == null))
			return true;

		ArrayList<Integer> l1 = getLeafs(root1, new ArrayList<Integer>());
		ArrayList<Integer> l2 = getLeafs(root2, new ArrayList<Integer>());

		if (l1.size() != l2.size())
			return false;

		for (int i = 0; i < l1.size(); i++) {
			Integer num1 = l1.get(i); //We're storing numbers in Integer classes as java == operator will only working between values 
										//-128 to 128 (by its caching mechanism), If we put == operator on bigger or smaller values outside
									//that range java will compare their memory address/ object ids not values hence we'll use Integer class
									//In which we can compare the actual content
			Integer num2 = l2.get(i);
			if (!num1.equals(num2))
				return false;
		}

			

		return true;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
//		root1.left.left = new TreeNode(6);
//		root1.left.right = new TreeNode(2);
//		root1.left.right.left = new TreeNode(7);
//		root1.left.right.right = new TreeNode(4);

		root1.right = new TreeNode(200);
//		root1.right.left = new TreeNode(9);
//		root1.right.right = new TreeNode(8);

		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
//		root2.left.left = new TreeNode(6);
//		root2.left.right = new TreeNode(7);

		root2.right = new TreeNode(200);
//		root2.right.left = new TreeNode(4);
//		root2.right.right = new TreeNode(2);
//		root2.right.right.left = new TreeNode(9);
//		root2.right.right.right = new TreeNode(8);

		System.out.println("Are leafs similar ? " + leafSimilar(root1, root2));

	}

}
