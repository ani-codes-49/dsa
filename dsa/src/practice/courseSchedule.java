package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class courseSchedule {
	
	///we will maintain 3 states 
	///0 - unvisited
	///1 - visited in the current path
	///2 - completed processing the node and that node does not produce a cycle in the graph
	
	static boolean dfs(List<List<Integer>> adj, int node,  int[] visited) {
		
		///base cases
		if(visited[node] == 1) return false; // cycle detected
		if(visited[node] == 2) return true; //already checked node (no cycle)
		
		///marking the node as 1 as we're exploring the path and have not finished full exploration
		visited[node] = 1;
		for(int i : adj.get(node)) { /// will iterate over every children of the current node
			
			boolean res = dfs(adj, i, visited);	 ///recursive dfs call
			if(!res) return false; ///if we have found a cycle in the graph then doesn't need to explore further
		}
		///as we completed process for the current node and its adjacent nodes
		///marking current node as 2 as we won't call this node again as we know this node does not produce a'
		///cycle in the graph
		///If It could produce a cycle in the graph then It would have returned false after hitting 1 and 
		///hitting our base case
		visited[node] = 2;
		
		///returning true if we do not find any cycle, helpful for remaining recursion
		return true;
	}

	
	///this problem is a topological sorting problem where if the graph does not have any cycle then we can take
	///all the courses otherwise not
	///
	///topological sort can only be applied if the graph is directed and acyclic
	static boolean canFinish(int numCourses, int[][] prerequisites) {
		
		if(numCourses <= 1) return true;
		
		///in this graph the node values are guaranteed to be less than the numCourses
		List<List<Integer>> adj = new ArrayList<>();
		
		///adding empty lists to adj list so that it won't cause null value error
		for(int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
		
		for(int i = 0; i < prerequisites.length; i++) {
			
//			int source = prerequisites[i][0];
//			int destination = prerequisites[i][1];
//			adj.get(source).add(destination);
			
			///building the graph
			adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
		}
		
		///visited array
		int[] visited = new int[numCourses];
		
		///calling the dfs method to check whether this graph produces cycle from any of the nodes
		///if yes we return false immediately 
		///We will go from 0 to numCourses - 1 as the graph vertex's value is guaranteed to be less than numCourses's value
		for(int i = 0; i < numCourses; i++) {
			
			if(visited[i] != 2) { ///optimal check for not calling already processed nodes 
								  ////hence not making extra call (extra stack space)
				boolean res = dfs(adj, i, visited);
				if(!res) return res;
			}
		}
		
		///If we do not find cycle anywhere in the graph we return true as all courses can be taken
		return true;
	}
	
	///Kahn's algorithm
	///BFS
	///If we want to take 0th course and 0th course depends on 1th course then
	///we build graph like 
	///1 ----> 0 (1 is prerequisite for 0th course) (according to kahn's algo)
	static boolean canFinishKahns(int numCourses, int[][] prerequisites) {
		
		///edge case
		if(prerequisites.length == 0) return true;
		
		List<List<Integer>> adj = new ArrayList<>(); ///adjacency list
		int[] inDegree = new int[numCourses]; ///inDegree of courses (number of incoming node at a particular node) 
		Queue<Integer> q = new LinkedList<>(); ///queue for BFS
		
		for(int i = 0; i < numCourses; i++) adj.add(new ArrayList<>()); ///adding empty lists
		
		for(int i = 0; i < prerequisites.length; i++) {
			
			///establishing links between graphs
			///if [0, 1] then 1 is a prerequisite for course 0 so,
			///connection will be 1 ----> 0
			int course = prerequisites[i][0];
			int prerequisite = prerequisites[i][1];
			
			adj.get(prerequisite).add(course);
			inDegree[course]++; ///inDegree for course 0 (in 1 ----> 0 case) will be increased 
			
		}
		
		///here we are adding courses(vertices) which inDegree is 0 (courses which don't have any prerequisites, 
		///and can be taken immediately')
		
		///note that we cannot use for each loop here as 
		///we have 0 to numCourses - 1 as vertices so,
		///if [1, 0] that means 0th index (vertex-0) has 1 incoming node and 1st index (vertex-1) has 0 incoming node
		///so if we use for each loop we will end up adding 0 instead of 1 as we have to add VERTEX and not its inDegree value
		for(int i = 0; i < inDegree.length; i++) if(inDegree[i] == 0) q.add(i);
		
		if(q.isEmpty()) return false; ///optimal check as if our queue is empty then we don't have any course(vertex)
									  ///which have inDegree as 0 every course has some prerequisite (has cycle)
									  ///hence our answer will be false

		int completedCourses = 0; ///this variable will track how much nodes we visit(can complete course)
								  ///as at this point queue has at least one or more nodes which can be taken immediately
		
		while (!q.isEmpty()) {
			
			int node = q.poll(); 
			completedCourses++; /// as we poll from queue we assume we visit that node and hence can complete it
								/// as queue has vertices which have no prerequisites (can be taken immediately)
			
			for(int neighbor : adj.get(node)) { ///we visit current vertex's neighbors and reduce their inDegree
												/// as if we complete current course then the course which we complete
												///is a prerequisite for its neighbor (we have built graph accordingly)
												///hence we reduce its neighbor's inDegree by 1
												///
												///after reducing its inDegree if that node has 0 inDegree then that node can
												///also be completed as its prerequisites has been fulfilled
												///(HAVING INCOMING EDGE TO ANY COURSE (VERTEX) MEANS,
												///FOR COMPLETING THAT COURSE (VERTEX)) WE HAVE TO COMPLETE THE COURSE FROM WHICH
												///THE EDGE IS COMING)
												///
												///so after reducing the inDegree of the neighbor if that neighbor has 0 inDegree
												///we add it in the queue
				if(--inDegree[neighbor] == 0) q.add(neighbor);
				
			}
			
		}
		///If we have completed (visited all the vertex(completed courses)) then we return true otherwise false
		return completedCourses == numCourses;
	}
	
	static void topologicalSort(int vertices, int[][] input) {
		
		List<List<Integer>> adj = new ArrayList<>();
		HashSet<Integer> vis = new HashSet<>();
		
		for (int i = 0; i < vertices; i++)
            adj.add(new ArrayList<>());
		
		for(int[] temp : input) adj.get(temp[0]).add(temp[1]);
		
		for(int i = 0; i < vertices; i++) {
			
			if(!vis.contains(i)) dfs(adj, i, vis);
			
		}
		
	}
	
	static void dfs(List<List<Integer>> adj, int v, HashSet<Integer> vis) {
		vis.add(v);
		System.out.print(v + " "); ///preOrder
		for(int neighbor : adj.get(v)) {
			if(!vis.contains(neighbor)) dfs(adj, neighbor, vis);
		}
//		System.out.print(v + " "); ///postOrder

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] input = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
		canFinishKahns(5, input);
		System.out.println("Topological Order: ");
		topologicalSort(5, input);
	}

}
