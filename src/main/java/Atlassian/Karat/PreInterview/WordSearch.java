package PreInterview;

public class WordSearch {


    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public static boolean exists(char[][] board, String word) {


        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {


                if (board[i][j] == word.charAt(0) && find(board, i, j, 0, m, n, word)) {

                    return true;
                }
            }
        }

        return false;
    }

    private static boolean find(char[][] board, int i, int j, int idx, int m, int n, String word) {


        if (idx == word.length()) return true;

        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] == '$') return false;

        if (board[i][j] != word.charAt(idx)) return false;


        char temp = board[i][j];

        board[i][j] = '$';


        for (int[] d : dir) {

            int i_ = d[0] + i;
            int j_ = d[1] + j;

            if (find(board, i_, j_, idx + 1, m, n, word)) {

                return true;
            }
        }


        board[i][j] = temp;
        return false;
    }


    public static void main(String[] args) {



        char[][] words = {{'A', 'B', 'C', 'E' }, {'S', 'F', 'C', 'S' }, {'A', 'D', 'E', 'E' }};

        String str = "ABCCED";


        boolean exists = exists(words, str);

        System.out.println(" word exists = " + exists);
    }
}
