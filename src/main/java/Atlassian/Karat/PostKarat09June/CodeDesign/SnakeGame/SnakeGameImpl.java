package PostKarat09June.CodeDesign.SnakeGame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SnakeGameImpl implements SnakeGame {


    List<Cell> snake;
    int boardWidth;
    int boardHeight;
    char[][] board;
    boolean isGameOver;
    Direction previousDirection;
    int steps;
    Cell foodPosition;
    Random random;


    SnakeGameImpl(int boardWidth, int boardHeight) {

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.steps = 0;
        this.isGameOver = false;
        this.snake = new LinkedList<>();
        this.snake.add(new Cell(0, 0));
        this.snake.add(new Cell(0, 1));
        this.snake.add(new Cell(0, 2));
        this.previousDirection = Direction.RIGHT;
        this.board = new char[boardWidth][boardHeight];
        this.random = new Random();
        this.foodPosition = generateFood();
    }


    public void populateBoard() {


        for (int i = 0; i < boardWidth; i++) {

            for (int j = 0; j < boardWidth; j++) {

                Cell cell = new Cell(i, j);

                if (!snake.contains(cell)) {

                    if (this.foodPosition.x == cell.x && this.foodPosition.y == cell.y) {

                        board[i][j] = 'F';
                    } else {

                        board[i][j] = '-';
                    }
                } else {


                    if (this.snake.getLast().x == cell.x && this.snake.getLast().y == cell.y) {

                        board[i][j] = '$';
                    } else {

                        board[i][j] = 'X';
                    }
                }
            }
        }
    }


    public void publishBoard() {

        for (int i = 0; i < boardWidth; i++) {

            for (int j = 0; j < boardHeight; j++) {

                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private Cell generateFood() {


        while (true) {

            int x = random.nextInt(boardWidth);
            int y = random.nextInt(boardHeight);

            Cell food = new Cell(x, y);

            if (!snake.contains(food)) {

                foodPosition = food;
                break;
            }
        }
        return foodPosition;
    }

    @Override
    public void moveSnake(Direction direction) {


        if (isGameOver)
            return;

        setDirection(direction);

        Cell newHead = getNextCell(direction);

        if (snake.contains(newHead)) {

            isGameOver = true;
            System.out.println(" snake bit itself...");
            return;
        }

        snake.add(newHead);
        steps++;

        if (steps % 5 == 0 || snake.contains(foodPosition)) {

            if (snake.contains(foodPosition)) {
                generateFood();
            }
        } else {

            this.snake.removeFirst();
        }

    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public void render() {

        populateBoard();
        publishBoard();
        System.out.println(" Snake : "+snake);
        System.out.println(" Food Position : "+foodPosition);
    }

    public void setDirection(Direction direction) {

        if (isValidDirection(direction)) {
            this.previousDirection = direction;
        }
    }

    private boolean isValidDirection(Direction direction) {


        if (this.previousDirection.equals(Direction.UP) && direction.equals(Direction.DOWN)
                || this.previousDirection.equals(Direction.LEFT) && direction.equals(Direction.RIGHT)
                || this.previousDirection.equals(Direction.RIGHT) && direction.equals(Direction.LEFT)
                || this.previousDirection.equals(Direction.DOWN) && direction.equals(Direction.UP)) {
            return false;
        }

        return true;
    }


    public Cell getNextCell(Direction direction) {

        Cell currentHead = snake.getLast();

        int x = currentHead.getX();
        int y = currentHead.getY();

        switch (direction) {

            case UP:
                x = (x - 1 + boardWidth) % boardWidth;
                break;
            case LEFT:
                y = (y - 1 + boardHeight) % boardHeight;
                break;
            case DOWN:
                x = (x + 1) % boardWidth;
                break;
            case RIGHT:
                y = (y + 1) % boardHeight;
                break;

        }

        return new Cell(x, y);
    }
}
