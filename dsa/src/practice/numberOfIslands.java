package practice;

public class numberOfIslands {

	private static void dfs(char[][] input, int i, int j) {
		
		///handling all the edge cases like index out of range and important check
		///as if we find any 0 (water region) then we don't have to do anything with it as its the water region hence we return
		///the water region breaks the island thats why returning 
		if(i < 0 || i >= input.length || j < 0 || j >= input[i].length || input[i][j] == '0') return;
		
		input[i][j] = '0'; //turning 1 to 0
		
		///will recursively call for left, right, up and down position for each cell that we're at
		dfs(input, i + 1, j); //down
		dfs(input, i - 1, j); //up
		dfs(input, i, j + 1); //right
		dfs(input, i, j - 1); //left
		return;
		
	}
	
	///we will find any 1 and travel all its adjacent 1s which will be one individual island; and mark all the respective 1s in that island
	///to 0 and increment our counter by 1
	///we will do this by applying bfs technique
	///
	///then after marking all 1s to 0 then we will continue traveling in the input grid
	///then again if we find any 1 we will repeat the same process
	static int islands(char[][] input) {
		
		int ans = 0;
		
		for(int i = 0; i < input.length; i++) {
			///normal traversal of matrix
			for(int j = 0; j < input[i].length; j++) {
				
				if(input[i][j] == '1') { ///if we find any 1 then we will visit all its adjacent 1s and turn them into 0s
										///and increment the counter by 1
					ans++;
					dfs(input, i, j); ///call to the function which will handle turning adjacent 1s to 0s
				}
				
			}
			
		}
		return ans;
	}

	public static void main(String[] args) {

		char[][] input = { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
				{ '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } };

	}

}
