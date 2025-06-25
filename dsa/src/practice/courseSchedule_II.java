package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class courseSchedule_II {
	
	static int[] ans; ///the final answer
	static int index; ///index to iterate through ans array
	static boolean hasCycle; ///boolean to check if the graph contains a cycle 
	
	static void dfs(List<List<Integer>> adj, int course, int[] vis) {
		
		///base cases
		///if we've found that a graph has a cycle then we return or if the node has already been processed and does not produce any cycle
		///then also we return
		if(hasCycle || vis[course] == 2) return;
		
		///if we have found  vis[course] == 1 then the graph contains cycle hence mark hasCycle var as true and return
		if(vis[course] == 1) {
			hasCycle = true;
			return;
		}
		
		///marking current node as 1 as we will mark 1 to the nodes discovered till now in the current path
		vis[course] = 1;
		
		///will traverse all neighbors of the current course (vertex)
		for(int i : adj.get(course)) dfs(adj, i, vis);
		
		///after we finish processing all the neighbors of the current node we mark the current node as 2 
		///as this node has not produced any cycle.
		///If It would then we have not reached here (hasCycle var shall be true)
		vis[course] = 2;
		
		///IMPORTANT PART
		
		///we are doing postorder traversal on this graph
		///postorder traversal processes all the neighbors/ dependencies first and then processes itself
		///
		///we are going till the depth of the graph and once we are at a node with no neighbors (no outgoing edges) 
		///we add that course (vertex) into the ans from the LAST
		///from last because we are going from (PREREQUISITE TO COURSE) 
		///and after we reach our vertex with no neighbors that vertex had all the prerequisite from which we came from
		///so the course(vertex) is obviously will not be the first, and hence it will come at the end
		///
		///the sole idea behind storing values from last is we want the vertices with no prerequisites first 
		///so if we go till the depth of the graph we will find vertex having prerequisites and that prerequisites are these from which
		///we came from (from which we made a call to its neighbor)
		///so being at the last node (from context of the depth) it should sit at last in the array as it has prerequisite
		///
		///THAT IS THE CORE IDEA BEHIND THIS PROBLEM

		ans[--index] = course;
		
	}

	///DFS
	///TC - O(V + E)
	///SC - O(V)
	
	static int[] findOrderDfs(int numCourses, int[][] prerequisites) {
		
		///instantiating essential variables
		ans = new int[numCourses];
		index = numCourses;
		hasCycle = false;
		
		List<List<Integer>> adj = new ArrayList<>(); ///adj list
		
		for(int i = 0; i < numCourses; i++) adj.add(new ArrayList<>()); ///filling
		
		///building adj list
		for(int[] temp : prerequisites) {
			int course = temp[0];
			int prerequisite = temp[1];
			
			adj.get(prerequisite).add(course);
		}
		
		///vis helper array to use it in the dfs
		int[] vis = new int[numCourses];
		 
		///making a dfs call starting from 0 to numCourses - 1
		for(int i = 0; i < numCourses; i++) 
				dfs(adj, i, vis);
		
		///If we have detected a cycle then we return an empty array else the actual answer
		///as if the graph contains cycle then we have a deadlock condition
		return hasCycle ? new int[0] : ans;
	}
	
	///Using BFS (Kahn's algo) 
	///TC - O(V + E)
	///SC - O(V)
	
	static int[] findOrder(int numCourses, int[][] prerequisites) {

		int[] ans = new int[numCourses]; ///ans array will be the size of the numCourses
		int index = 0; ///index var for iterating over ans array
		
		List<List<Integer>> adj = new ArrayList<>(); ///adj list
		int[] inDegree = new int[numCourses]; ///inDegree since we are using bfs
		
		for(int i = 0; i < numCourses; i++) adj.add(new ArrayList<>()); ///ading empty lists
		
		for(int[] temp : prerequisites) { ///building adj list
			int course = temp[0];
			int prerequisite = temp[1];
			
			adj.get(prerequisite).add(course); ///forming connections like prerequisite --------> course
			inDegree[course]++; ///inDegree of the course will be increased as it will get an incoming edge
		}
		
		///queue for bfs
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0; i < inDegree.length; i++) if(inDegree[i] == 0) q.add(i); ///we will start from the vertices having inDegree as 0 
																				/// as they can be taken immediately
		
		
		while  (!q.isEmpty()) {
			
			int course = q.poll(); ///getting first node
			ans[index++] = course; ///adding it in the answer as pulling out a node means we complete a course (vertex)
									///and as our queue only holds nodes with inDegree as 0 so pulling out a node will always have a 
									///inDegree as 0 and can be taken right away
			
			for(int j : adj.get(course)) { ///will reduce the inDegree of the current course's (vertex's) neighbors
				
				if(--inDegree[j] == 0) q.add(j); ///after reducing the inDegree of the neighbor vertex, If the new inDegree of that node
												 ///has became 0 then we will add it in the queue as it can be taken immediately
				
			}

		}
		
		///if we have not processed every node then we have not processed the whole graph and that means the graph contains a cycle
		///which is deadlock condition in the case of course prerequisite i.e 2 courses have prerequisite as them each other,
		///eg: (1 -----> 0)
		///     ^
		///		|        |
		///      ---------
		return index == numCourses ? ans : new int[0];///kahn's algorithm checks for cycles in the graph by verifying whether we have 
														///processed all nodes or not (index var in our case, if index == numCourses
														///	that means we have touched all nodes and cycle isn't present)
														///hence if we have reached till numCourses then we return the ans array else []
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numCourses = 3;
		int[][] input = {{1, 0}, {0, 1}, {1, 2}};
		
		int[] a = findOrderDfs(numCourses, input);
		for(int i : a) System.out.print(i + " ");
	}

}
