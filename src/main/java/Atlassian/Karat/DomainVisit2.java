

import java.util.ArrayList;
import java.util.List;

public class DomainVisit2 {


    public static List<String> commonBrowsingHistory(String[] user1History, String[] user2History) {


        int m = user1History.length;
        int n = user2History.length;

        List<String> result = new ArrayList<>();

        int[][] dp = new int[m+1][n+1];

        int maxLength = 0;
        int endIndex = -1;

        for (int i = 1; i <= user1History.length; i++) {

            for (int j = 1; j <= user2History.length; j++) {

                if (user1History[i - 1].equals(user2History[j - 1])) {

                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > maxLength) {

                        maxLength = dp[i][j];
                        endIndex = i - 1;
                    }

                }
            }


        }

        if (maxLength > 0) {

            for (int i = maxLength - endIndex + 1; i <= endIndex; i++) {


                result.add(user1History[i]);

            }
        }

        return result;
    }


    public static void main(String[] args) {


        String[] user1 = {"3234.html", "xys.html", "7hsaa.html"};
        String[] user2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};

        List<String> result = commonBrowsingHistory(user1, user2);
        System.out.println(result);
    }
}