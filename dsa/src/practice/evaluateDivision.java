package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class evaluateDivision {

	///Node for adjacency list
	///It will hold destination node and weight to reach the destination
	///the source will be stored in the form of key inside a hashmap
	static class Node {

		String destination;
		double weight;

		public Node(String d, double w) {
			this.destination = d;
			this.weight = w;
		}

	}

	///helper method will behave in DFS manner
	static double helper(HashMap<String, List<Node>> adj, String s, String d, HashSet<String> vis) {

		///base case, If we reach any node and If that node is equal to the destination then we will return 1.0 so that this 1.0
		///can be multiplied with the edge weights
		if (s.equals(d))
			return 1.0;
		///we're at a node so we will mark it as visited so that we won't process it multiple times
		vis.add(s);
		
		///we will iterate over every connected node of a current node
		for (Node n : adj.get(s)) {

			///if the connected node on which we are going at is not visited then only we will jump on that node
			if (!vis.contains(n.destination)) {
				///for calculating the expression we will go till the depth (DFS until we reach the destination)
				///
				///We will call the recursion first and calculate the answer later
				///because we need to make sure we have found our destination otherwise if we calculate the answer before 
				///calling the recursion (finding our destination) and if that path does not contain our destination then we have 
				///to revert all things (calculations back) which is incorrect logic
				///
				///For this issue we call the recursion first then if we hit our base case (found our destination) then only
				///we will return 1.0
				///Now after recursion we will check whether we have received a non-negative value or not
				///If we do, then we multiply returned value with the weight of the outgoing edge
				///else we do nothing and explore other vertices
				///
				///
				double product = helper(adj, n.destination, d, vis);
				///Our recursion will return us non negative value if we have found our destination otherwise
				///if it has explored a path and hasn't found our destination then it will return -1
				///
				///If we get -1 then we'll do nothing and we simply explore remaining vertices
				///
				///If we got 1.0 then we definitely have found our destination (according to the base case)
				///If we have got a non negative value then we return [received value * n.weight] (weight for going to the destination,
				/// basically from which we came from) 
				///
				///this formula is a mathematical expression rule so following it
				if(product != -1) return product * n.weight;
			}

		}
		///If we're at any node and we have not found our destination nor we have unvisited neighbors then we have finished iterating'
		///over neighbors and hence we return -1
		///returning -1 helps understand the previous nodes that this path does not contain our destination and we have to take remaining path
		///instead of returning straight away (exiting from recursion) which is incorrect
		return -1.0;
	}

	static double[] evaluate(List<List<String>> equations, double[] values, List<List<String>> queries) {

		///declaring answer array as the size of the queries array because we have to give answer to all the queries
		double[] ans = new double[queries.size()];
		int index = 0;///index for storing elements in a ans array

		///value of map will be a list of nodes because any node can have multiple vertexes connected to them
		HashMap<String, List<Node>> adj = new HashMap<>(); ///adjacency list for storing graph connections
		///in adjacency list (map) keys will be source node and the values will be a Node() where node.destination will be a destination from source
		/// and node.weight will be a weight required to reach the destination from source
		
		///using hashset for storing visited node's array 
		///Only declaring as we need to reset the set between queries
		HashSet<String> vis; 

		for (int i = 0; i < equations.size(); i++) {

			String src = equations.get(i).get(0);
			String dest = equations.get(i).get(1);

			///adding empty lists to the available nodes
			/// so that we do not have to check whether we already have a list at specific node or not
			adj.putIfAbsent(src, new ArrayList<>());
			adj.putIfAbsent(dest, new ArrayList<>());
			
			///making connections and assigning weights
		
			adj.get(src).add(new Node(dest, values[i])); /// for given equation we will simply use the i'th value given in the values array
			adj.get(dest).add(new Node(src, 1 / values[i])); ///for connecting node back we set value as
															/// [1 / value[i]] i.e (if a/b = 2 then a ----> b = 2 && b ------> a = 1 / 2) (math rule)

		}

		///for answering queries,
		for (int i = 0; i < queries.size(); i++) {
			///if any of the query contains a variable that does not exist in the graph then answer for that is -1.0 (according to problem stat....)
			if (!adj.containsKey(queries.get(i).get(0)) || !adj.containsKey(queries.get(i).get(1))) {
				ans[index++] = -1.00;
			} else if (queries.get(i).get(0).equals(queries.get(i).get(1))) { ///if both variables in a query are same then their division will always be 1
				ans[index++] = 1.00;
			} else {  
				vis = new HashSet<>(); ///reset the set each time we are going to answer a query as all queries are independant of each other
				ans[index++] = helper(adj, queries.get(i).get(0), queries.get(i).get(1), vis);
			}
		}
		///finally returning the ans
		return ans;
	}

	public static void main(String[] args) {
		List<List<String>> equations = new ArrayList<>();
		equations.add(Arrays.asList("a", "b"));
		equations.add(Arrays.asList("b", "c"));
		equations.add(Arrays.asList("bc", "cd"));

		double[] values = new double[equations.size()];
		values[0] = 1.5;
		values[1] = 2.5;
		values[2] = 5.0;

		List<List<String>> queries = new ArrayList<>();

		queries.add(Arrays.asList("a", "c"));
		queries.add(Arrays.asList("c", "b"));
		queries.add(Arrays.asList("bc", "cd"));
		queries.add(Arrays.asList("cd", "bc"));

		double[] ans = evaluate(equations, values, queries);
		for (double i : ans)
			System.out.print(i + " ");
	}

}
