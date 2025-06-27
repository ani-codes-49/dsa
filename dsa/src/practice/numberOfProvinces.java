package practice;

import java.util.BitSet;

public class numberOfProvinces {
	
	///we will avoid calling self loops by
	///suppose we are at vertex 0 and if columnIndex is also 0, and if
	///connections[vertex][columnIndex] == 1 then 
	///vertex will be marked as visited i.e 0
	///and when we are inside a loop at i = 0 i.e if connections[vertex(0)][i(0)] == 1
	///we won't make another call as 0 is already marked visited outside the loop as vertex which was passed to this function
	///is also 0
	
	static void dfs(int[][] connections, int vertex, BitSet vis) {
		
		///marking the current vertex as visited
		vis.set(vertex); ///bit at vertex index sets to 1
		
		
		///run loop as the size of the columns (horizontally)
		///as the columns are destination nodes
		///and we use the same rowIndex (vertex which is passed to this function) for each iteration 
		///as we will check the neighbors of the passed vertex
		///so using vertex in rowIndex
		for(int i = 0; i < connections.length; i++) {
			
			///if any connections[vertex][i] == 1 it means we have a neighbor from vertex and we pass that neighbor in recursive call
			///if its not visited
			if(connections[vertex][i] == 1 && !vis.get(i)) {
				dfs(connections, i, vis);
			}
			
		}
		
	}

	///Idea is just call dfs for each unvisited node and when it finishes increment our answer
	///So that it will run for each region
	static int number(int[][] connections) {
		
		///not creating adjacency list here as the input is adjacency matrix
		///
		///IN ADJ MATRIX we consider rows index as the source node of the graph and the column index as the destination node
		///if any mat[rowIndex][columnIndex] == 1 then we consider a connection between them and pass the vertex at column index for dfs
		///In adj matrix the source vertex is the one at rowIndex and destination vertex is at columnIndex
		///So basically the vertex we pass to the dfs function is considered as source node in the dfs function
		///and source vertex are checked on rowIndexes in adj matrix so we use the passed vertex as the rowIndex (source) in the dfs method
		///as we need to check its adjacent vertices 
		///
		///
		///
		
		int ans = 0; ///final answer
		BitSet vis = new BitSet(connections.length); ///using bitset for efficient memory access
		
		///calling dfs for each unvisited node
		///
		for(int i = 0; i < connections.length; i++) {
			
			if(!vis.get(i)) { ///if the received bit is not set (0) then we call dfs for that node as it is unvisited
				dfs(connections, i, vis);
				ans++; ///after finishing dfs we increment our answer
			}
			
		}
		return ans; ///returning the answer
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
