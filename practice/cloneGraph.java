package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class cloneGraph {
	
	///storage for storing newly created nodes and accessing them in o(1)
	static HashMap<Integer, Node> storage = new HashMap<>();
	
	static Node clone(Node node) {
		
		///base cases
		if(node == null) return node;
		///if we already have a node present in the map then we simply return it instead of processing it again
		if(storage.containsKey(node.val)) return storage.get(node.val); 
		
		///if its not stored in the map then we store it in the map
		storage.put(node.val, new Node(node.val));
		///we create a new node with the value of the node we're currently at
		Node newNode = storage.get(node.val);
		
		///we traverse the neighbors of the current node we're at and call recursion for each neighbor
		///when recursion will eventually return us a newly created node we add that node into our newly created node (outside the loop)
		for(Node n : node.neighbors) newNode.neighbors.add(clone(n));
		
		///then we return our new node which will be useful for the remaining recursion
		return newNode;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Node> nodes1 = new ArrayList<>();
		ArrayList<Node> nodes2 = new ArrayList<>();
		ArrayList<Node> nodes3 = new ArrayList<>();
		ArrayList<Node> nodes4 = new ArrayList<>();
		
		Node n1 = new Node(1, nodes1);
		Node n2 = new Node(2, nodes2);
		Node n3 = new Node(3, nodes3);
		Node n4 = new Node(4, nodes4);
		
		nodes4.add(n1);
		nodes4.add(n3);
		
		nodes3.add(n4);
		nodes3.add(n2);
		
		nodes2.add(n3);
		nodes2.add(n1);
		
		nodes1.add(n2);
		nodes1.add(n4);

		clone(n1);
	}

}
