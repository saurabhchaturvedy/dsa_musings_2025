package KaratFriday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FindRectangles {


    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    public static List<List<int[]>> findRectangles(int[][] image) {

        int row = image.length;
        int col = image[0].length;

        boolean[][] visited = new boolean[row][col];
        List<List<int[]>> result = new ArrayList<>();

        for (int r = 0; r < row; r++) {

            for (int c = 0; c < col; c++) {

                if (image[r][c] == 0 && !visited[r][c]) {

                    List<int[]> island = new ArrayList<>();
                    dfs(image, visited, r, c, island);
                    result.add(island);
                }


            }
        }

        return result;
    }

    private static void dfs(int[][] image, boolean[][] visited, int r, int c, List<int[]> island) {


        int row = image.length;
        int col = image[0].length;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{r, c});
        visited[r][c] = true;

        while (!stack.isEmpty()) {

            int[] cell = stack.pop();
            island.add(new int[]{cell[0],cell[1]});

            for (int[] d : dir) {

                int newRow = cell[0] + d[0];
                int newCol = cell[1] + d[1];

                if (newRow >= 0 && newCol >= 0 && newRow < row && newCol < col && image[newRow][newCol] == 0 && !visited[newRow][newCol]) {

                    stack.push(new int[]{newRow, newCol});
                    visited[r][c] = true;

                }
            }

        }
    }


    public static void main(String[] args) {

        int[][] image1 = {
                {1, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 1, 1},
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 1, 1, 0},
        };

        List<List<int[]>> shapes = findRectangles(image1);

        for (List<int[]> shape : shapes) {
            System.out.print("[");
            for (int[] cell : shape) {
                System.out.print(Arrays.toString(cell) + ",");
            }
            System.out.println("]");
        }
    }
    }

