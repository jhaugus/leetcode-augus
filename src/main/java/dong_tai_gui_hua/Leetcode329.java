package dong_tai_gui_hua;
//329. 矩阵中的最长递增路径
// 二维数组，可以向上下左右移动，找出最长递增路径
public class Leetcode329 {




    // dfs超时
    // 先dfs试一下
    private static int max = 0;
//    public static int longestIncreasingPath(int[][] matrix) {
//        int n = matrix.length;
//        int m = matrix[0].length;
//        if(n == 1 && m == 1) return 1;
//        for(int i = 0 ; i < n; i++){
//            for(int j = 0; j < m; j++){
//                int temp = matrix[i][j];
//                matrix[i][j] = -1;
//                dfs(matrix, i, j, n, m, 1, temp);
//                matrix[i][j] = temp;
//            }
//        }
//        return max;
//    }

    public static void dfs(int[][] matrix, int i, int j, int n, int m, int cur, int curValue){
        if(cur > max){
            max = cur;
        }

        if (i - 1 >= 0 && matrix[i - 1][j] != -1 && matrix[i-1][j] > curValue) {
            int temp = matrix[i - 1][j];
            matrix[i - 1][j] = -1;
            dfs(matrix, i - 1, j, n, m ,cur + 1, temp);
            matrix[i - 1][j] = temp;

        }
        if(i + 1 < n && matrix[i + 1][j] != -1 && matrix[i+1][j] > curValue){
            int temp = matrix[i + 1][j];
            matrix[i + 1][j] = -1;
            dfs(matrix, i + 1, j, n, m ,cur + 1, temp);
            matrix[i + 1][j] = temp;
        }

        if(j - 1 >= 0 && matrix[i][j - 1] != -1 && matrix[i][j-1] > curValue){
            int temp = matrix[i][j - 1];
            matrix[i][j - 1] = -1;
            dfs(matrix, i, j - 1, n, m ,cur + 1, temp);
            matrix[i][j - 1] = temp;
        }

        if(j + 1 < m && matrix[i][j + 1] != -1 && matrix[i][j+1] > curValue){
            int temp = matrix[i][j + 1];
            matrix[i][j + 1] = -1;
            dfs(matrix, i, j + 1, n, m ,cur + 1, temp);
            matrix[i][j + 1] = temp;
        }
    }

}
