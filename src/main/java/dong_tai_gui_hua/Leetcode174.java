package dong_tai_gui_hua;
// 174. 地下城游戏
// 求从二维数组左上角到右下角的需要的最少生命值
// 二维数组有负数、0、正数
// dp[i][j] = Math.max(1, Math.min(dp[i-1][j], dp[i][j-1]) - dungeon[i][j])
public class Leetcode174 {
    public int calculateMinimumHP(int[][] dungeon) {

        int n = dungeon.length;
        int m = dungeon[0].length;
        // dp[i][j]表示从(i,j)到右下角需要的最少生命值
        int[][] dp = new int[n][m];
        dp[n-1][m-1] = Math.max(1, 1 - dungeon[n-1][m-1]);
        for(int i = m - 2; i >= 0; i--){
            dp[n - 1][i] = Math.max(1, dp[n-1][i+1] - dungeon[n-1][i]);
        }
        for(int i = n - 2; i >= 0; i--){
            dp[i][m - 1] = Math.max(1, dp[i+1][m-1] - dungeon[i][m-1]);
        }

        for(int i = n - 2; i >= 0; i--){
            for(int j = m - 2; j >= 0; j--){
                dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]);
            }
        }


        return dp[0][0];
    }
}
