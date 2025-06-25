package practice;

public class captureRegions {

	private static void boundaryDFS(char[][] input, boolean[][] vis, int i, int j) {

		///edge cases for indexes if they fall outside the valid boundaries of a map
		///or even if any cell != 'O' as we want to find adjacent O's which are regions according to the problem statement
		///or if we've already visited the node
		///in all these above cases we will return
		if (i < 0 || i > input.length - 1 || j < 0 || j > input[i].length - 1 || input[i][j] != 'O' || vis[i][j]) 
			return;

		vis[i][j] = true; ///marking true as we've done with this cell

		///dfs calls
		boundaryDFS(input, vis, i + 1, j); // down
		boundaryDFS(input, vis, i - 1, j); // up
		boundaryDFS(input, vis, i, j + 1); // right
		boundaryDFS(input, vis, i, j - 1); // left

		return;

	}
	
	///The idea is to scan all the boundary rows and columns and if any O's are present there mark them as visited as we won't visit them twice
	///We can do it without using visited array but it will cause traversals to visit the node multiple times even if we already processed it
	///In this case we can mark 0's that are on boundary rows/columns and turn them into * and later scan the whole graph and when we see a *
	///we can be assured that it is lying on the boundary hence we turn them into O as O's which are on the boundary cannot be surrounded by X
	///
	///So in this approach (visited array) will instantiate a new array (m x n) array 

	///we have to capture regions which are covered by X (by horizontally or vertically)
	///hence if any region containing O is on the boundary we cannot capture it because its on the boundary and its not properly surrounded by X
	
	static void solve(char[][] input) {
		
		int rows = input.length;
		int cols = input[0].length;
		
		///visited array
		boolean[][] vis = new boolean[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) vis[i][j] = false;
		}

		///SCANNING ROWS MEANS GOING FROM TOP TO BOTTOM
		///SCANNING COLUMNS MEANS GOING FROM LEFT TO RIGHT
		
		
		// checking boundary rows
		for (int i = 0; i < rows; i++) { /// i < rows because we have to scan border rows [<ROWS INDEX>][<COLUMN INDEX>] 
										 /// and the length of the rows can be achieved by input.length
			if (input[i][0] == 'O' && !vis[i][0]) ///if we see any O on the boundary and if its not visited yet we send it for scanning
												  ///scanning means we search the O's which are connected to the (O's which are on boundary)
												  ///and hence that whole region cannot be surrounded as one of the O in that region is on boundary
				boundaryDFS(input, vis, i, 0);
			if (input[i][cols - 1] == 'O' && !vis[i][cols - 1])	///input[i][cols - 1] because even if we are searching rows we have to set column
																///index 
																/// 0    [cols - 1]
																///	X  X  X
																/// X  X  O
																/// X  O  X
				boundaryDFS(input, vis, i, cols - 1);
		}

		// checking for columns
		for (int i = 0; i < cols; i++) { /// i < cols because we have to scan border columns [<ROWS INDEX>][<COLUMN INDEX>] 
			if (input[0][i] == 'O' && !vis[0][i])
				boundaryDFS(input, vis, 0, i);
			if (input[rows - 1][i] == 'O' && !vis[rows - 1][i]) ///input[0][i]
																///input[rows - 1][i] because
																///	 
																///	X  X  X <-- 0
																/// X  X  O
																/// X  O  X <-- rows - 1
				boundaryDFS(input, vis, rows - 1, i);
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (input[i][j] == 'O' && !vis[i][j]) ///checking other O's and the ones which aren't visited yet
													  ///visited nodes will be nodes which form a region with (O's which are on boundary)
													  ///or the ones which are stand alone and are on the boundary row/column
					input[i][j] = 'X'; ///capturing the capturable regions
									   ///remaining X's will be always valid regions (X's) as we have already checked O's which are on boundaries
									   ///and regions which forms themselves with boundary O's AND WE HAVE VISITED ALL THESE NODES (marked as visited)
									   ///and if we are inside this block then we have definitely not visited this
									   ///node hence this node is a valid region which can be captured hence marking this as X
			}
		}

		return;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] input = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' } };
		long startTime = System.nanoTime();
		solve(input);
		long endTime = System.nanoTime();
		System.out.println("Time taken: " + (endTime - startTime) + "ms");
		
		boolean[][] temp = new boolean[5][5];
		
		
	}

}
