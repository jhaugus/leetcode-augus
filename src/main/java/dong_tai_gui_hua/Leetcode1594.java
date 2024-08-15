package dong_tai_gui_hua;
// 矩阵的最大非负积
// 计算从左上角到右下角的最大乘积的路径
// 返回最大乘积
// 路径上的元素有正数有负数


public class Leetcode1594 {
    public int maxProductPath(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        long[][] dpMax = new long[n][m];
        long[][] dpMin = new long[n][m];
        dpMin[0][0] = grid[0][0];
        dpMin[0][0] = grid[0][0];


        for(int i = 1 ; i < m; i++){
            dpMax[0][i] = dpMax[0][i-1] * grid[0][i];
            dpMin[0][i] = dpMin[0][i-1] * grid[0][i];
        }

        for(int i = 1 ; i < n; i++){
            dpMax[i][0] = dpMax[i-1][0] * grid[i][0];
            dpMin[i][0] = dpMin[i-1][0] * grid[i][0];
        }

        for(int i = 1 ; i < n; i++){
            for(int j = 1 ; j < m; j++){
                if(grid[i][j] > 0){
                    dpMax[i][j] = Math.max(dpMax[i-1][j], dpMax[i][j-1]) * grid[i][j];
                    dpMin[i][j] = Math.min(dpMin[i-1][j], dpMin[i][j-1]) * grid[i][j];
                }else{
                    dpMax[i][j] = Math.min(dpMax[i-1][j], dpMax[i][j-1]) * grid[i][j];
                    dpMin[i][j] = Math.max(dpMin[i-1][j], dpMin[i][j-1]) * grid[i][j];
                }
            }
        }

        long result = dpMax[n-1][m-1];
        if(result < 0){
            return -1;
        }else{
            return (int) result % 1000000007;
        }


    }
}
