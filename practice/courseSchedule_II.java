package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class courseSchedule_II {
	
	static int[] ans;
	static int index;
	
	static void dfs(List<List<Integer>> adj, int course, int[] vis) {
		
			if(vis[course] == 1) {
				index = -1;
				return;
			} if(vis[course] == 2) return;
		ans[index++] = course;
		vis[course] = 1;
		for(int i : adj.get(course)) {
			dfs(adj, i, vis);
		}
		vis[course] = 2;
		
	}

	static int[] findOrderDfs(int numCourses, int[][] prerequisites) {
		
		ans = new int[numCourses];
		index = 0;
		
		List<List<Integer>> adj = new ArrayList<>();
		
		for(int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
		
		for(int[] temp : prerequisites) {
			int course = temp[0];
			int prerequisite = temp[1];
			
			adj.get(prerequisite).add(course);
		}
		int[] vis = new int[numCourses];
		
		for(int i = 0; i < numCourses; i++) {
				dfs(adj, i, vis);
			
		}
		
		return index == numCourses ? ans : new  int[0];
	}
	
	static int[] findOrder(int numCourses, int[][] prerequisites) {

		int[] ans = new int[numCourses];
		int index = 0;
		
		List<List<Integer>> adj = new ArrayList<>();
		int[] inDegree = new int[numCourses];
		
		for(int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
		
		for(int[] temp : prerequisites) {
			int course = temp[0];
			int prerequisite = temp[1];
			
			adj.get(prerequisite).add(course);
			inDegree[course]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0; i < inDegree.length; i++) if(inDegree[i] == 0) q.add(i);
		
		
		while  (!q.isEmpty()) {
			
			int course = q.poll();
			ans[index++] = course;
			
			for(int j : adj.get(course)) {
				
				if(--inDegree[j] == 0) q.add(j);
				
			}

		}
		return index == numCourses ? ans : new int[0];
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numCourses = 3;
		int[][] input = {{1, 0}, {0, 1}, {1, 2}};
		
		int[] a = findOrderDfs(numCourses, input);
		for(int i : a) System.out.print(i + " ");
	}

}
