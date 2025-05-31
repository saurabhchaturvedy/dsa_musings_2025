package PreInterview;

import java.util.ArrayList;
import java.util.List;

public class DomainVisit2 {


    public static List<String> commonHistory(String[] userHistory1, String[] userHistory2) {


        int m = userHistory1.length;
        int n = userHistory2.length;

        int[][] dp = new int[m + 1][n + 1];

        int maxLength = 0;
        int endIndex = -1;


        for (int i = 1; i <= userHistory1.length; i++) {

            for (int j = 1; j <= userHistory2.length; j++) {


                if (userHistory1[i - 1].equals(userHistory2[j - 1])) {


                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > maxLength) {


                        maxLength = dp[i][j];
                        endIndex = i - 1;
                    }
                }
            }
        }


        List<String> result = new ArrayList<>();

        if (maxLength > 0) {

            for (int i = maxLength - endIndex + 1; i <= endIndex; i++) {


                result.add(userHistory1[i]);
            }

        }


        return result;

    }


    public static void main(String[] args) {


        String[] user1 = {"3234.html", "xys.html", "7hsaa.html"};
        String[] user2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};

        List<String> result = commonHistory(user1, user2);
        System.out.println(result);
    }
}
