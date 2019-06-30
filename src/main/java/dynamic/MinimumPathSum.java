package dynamic;

public class MinimumPathSum {

    public static void main(String[] args) {
        MinimumPathSum obj = new MinimumPathSum();
    }

    // leetcode 64. Minimum Path Sum
    // Given a m x n grid filled with non-negative numbers,
    // find a path from top left to bottom right which minimizes the sum
    // of all numbers along its path.
    public int minPathSum(int[][] grid) {

        // 先算两个边
        int cols = grid[0].length;
        int rows = grid.length;

        for (int i = 1; i < rows; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int j = 1; j < cols; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }

        return grid[rows - 1][cols - 1];
    }
}
