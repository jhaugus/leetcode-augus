package dong_tai_gui_hua;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.List;

// 1301. 最大得分的路径数目
// 给定正方形board，右下角是S,左下角是E，需要从S走到E
// 其他地方是X或者数字，X表示障碍，走不通
// 返回从S走到E的最大得分score，以及最大得分的方案数num,即int[]{score, nums}
// 结果与 1000000007取模

// 太棒啦 ，自己做出来的

public class Leetcode1301 {

    private final static int MOD = 1000000007;
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        long[][] boardInt = new long[n][n];

        for (int i = 0; i < n; i++) {
            String s = board.get(i);
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                if (c == 'X') {
                    boardInt[i][j] = -1;
                } else if (c == 'E') {
                    boardInt[i][j] = 0;
                } else if (c == 'S') {
                    boardInt[i][j] = 0;
                }else{
                    boardInt[i][j] = c - '0';
                }
            }
        }

        long[][] dp = new long[n][n];
        long[][] countDp = new long[n][n];
        // dp记录最大得分
        // countDp记录最大得分的方案数
        // dp[i][j]表示从右下角到(i,j)的最大得分
        // if(dp[i+1][j+1] == -1 && dp[i+1][j] == -1 && dp[i][j+1] == -1) dp[i][j] = -1;
        // if(broadInt[i][j] == -1) dp[i][j] = -1 countDp[i][j] = -1;
        // else dp[i][j] = max(dp[i+1][j+1], dp[i+1][j], dp[i][j+1]) + broadInt[i][j];
        // max = max(dp[i+1][j+1], dp[i+1][j], dp[i][j+1])
        // if(max == dp[i+1][j+1]) countDp[i][j] += max(countDp[i+1][j+1];
        // if(max == dp[i+1][j]) countDp[i][j] += countDp[i+1][j];
        // if(max == dp[i][j+1]) countDp[i][j] += countDp[i][j+1];




        // 初始化
        dp[n-1][n-1] = 0;
        countDp[n-1][n-1] = 1;
        for(int i = n -2 ; i >= 0; i--){
            if(dp[i+1][n-1] == -1 || boardInt[i][n-1] == -1){
                dp[i][n-1] = -1;
                countDp[i][n-1] = -1;
            }else{
                dp[i][n-1] = dp[i+1][n-1] + boardInt[i][n-1];
                countDp[i][n-1] += countDp[i+1][n-1];
            }
        }
        for(int i = n -2 ; i >= 0; i--){
            if(dp[n-1][i+1] == -1 || boardInt[n-1][i] == -1){
                dp[n-1][i] = -1;
                countDp[n-1][i] = -1;
            }else{
                dp[n-1][i] = dp[n-1][i+1] + boardInt[n-1][i];
                countDp[n-1][i] += countDp[n-1][i+1];
            }
        }



        for(int i = n -2 ; i >= 0; i--){
            for(int j = n -2 ; j >= 0; j--){
                if((dp[i+1][j+1] == -1 && dp[i+1][j] == -1 && dp[i][j+1] == -1) || boardInt[i][j] == -1){
                    dp[i][j] = -1;
                    countDp[i][j] = -1;
                }else{
                    long max = Math.max(dp[i+1][j+1], Math.max(dp[i+1][j], dp[i][j+1]));
                    dp[i][j] = max + boardInt[i][j];
                    if(max == dp[i+1][j+1]){
                        countDp[i][j] += countDp[i+1][j+1];
                        countDp[i][j] %= MOD;
                    }
                    if(max == dp[i+1][j]){
                        countDp[i][j] += countDp[i+1][j];
                        countDp[i][j] %= MOD;
                    }
                    if(max == dp[i][j+1]){
                        countDp[i][j] += countDp[i][j+1];
                        countDp[i][j] %= MOD;
                    }
                }
            }
        }

        if(dp[0][0] == -1){
            dp[0][0] = 0;
            countDp[0][0] = 0;
        }
        return new int[]{(int)dp[0][0] % MOD, (int)countDp[0][0]% MOD};
    }
}
