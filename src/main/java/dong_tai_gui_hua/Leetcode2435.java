package dong_tai_gui_hua;
// 2435. 矩阵中和能被 K 整除的路径
// 二维数组从左上角到右下角，数组只有正数
// 从左上角到右下角的路径和中如果整除k，则res ++

public class Leetcode2435 {

    public static int numberOfPaths(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] count = new int[n+1][m+1][k];

        count[0][1][0] = 1;

        for(int i = 0 ; i < n; i ++){
            for(int j = 0 ; j < m; j++){
                for(int e = 0; e < k; e++){
                    count[i+1][j+1][(e + grid[i][j]) % k] = (count[i+1][j][e] + count[i][j+1][e]) % 1000000007;
                }
            }
        }
        return count[n][m][0];


    }


    // dfs超时
    private static int res;
//    public int numberOfPaths(int[][] grid, int k) {
//        res = 0;
//        dfs(grid, grid.length, grid[0].length, 0, 0, 0, k);
//        return res;
//    }

    public static void dfs(int[][] grid, int n, int m, int line, int row, int count, int k){
        if(line == n - 1 && row == m - 1&& (count + grid[n-1][m-1]) % k == 0){
            res++;
        }
        if(line < n - 1){
            dfs(grid, n, m, line + 1, row, count + grid[line][row], k);
        }
        if(row < m - 1){
            dfs(grid, n, m, line, row + 1, count + grid[line][row], k);
        }
    }
}
