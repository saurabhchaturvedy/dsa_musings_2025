package Post14June.Design.SnakeGame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SnakeGameImpl implements SnakeGame {


    int boardWidth;
    int boardHeight;
    List<Cell> snake;
    volatile Direction previousDirection;
    char[][] board;
    int steps = 0;
    Random random;
    volatile boolean isGameOver;
    Cell foodPosition;


    SnakeGameImpl(int boardWidth, int boardHeight) {

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.snake = new LinkedList<>();
        this.snake.add(new Cell(0, 0));
        this.snake.add(new Cell(0, 1));
        this.snake.add(new Cell(0, 2));
        this.random = new Random();
        this.isGameOver = false;
        this.board = new char[boardWidth][boardHeight];
        this.previousDirection = Direction.RIGHT;
        this.foodPosition = generateFood();
    }


    public void start() {


        Thread thread = new Thread(() -> {


            while (!isGameOver) {

                moveSnake(previousDirection);
                render();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
    }


    public void populateBoard() {

        for (int i = 0; i < boardWidth; i++) {

            for (int j = 0; j < boardHeight; j++) {

                Cell cell = new Cell(i, j);

                if (!snake.contains(cell)) {

                    if (cell.getX() == foodPosition.getX() && cell.getY() == foodPosition.getY()) {


                        board[i][j] = 'F';

                    } else {

                        board[i][j] = '-';
                    }

                } else {


                    if (snake.getLast().getX() == cell.getX() && snake.getLast().getY() == cell.getY()) {

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


    @Override
    public synchronized void moveSnake(Direction direction) {


        if (isGameOver)
            return;


        setDirection(direction);


        Cell nextPosition = getNextPosition(direction);

        if (snake.contains(nextPosition)) {

            isGameOver = true;
            System.out.println(" Snake has bit itself");
        }

        steps++;

        snake.add(nextPosition);

        if (steps % 5 == 0 || snake.contains(foodPosition)) {

            if (snake.contains(foodPosition)) {

                generateFood();
            }
        } else {

            snake.removeFirst();
        }


    }


    public synchronized void setDirection(Direction direction) {

        if (isValidDirection(direction)) {

            isValidDirection(direction);
        }

    }


    public boolean isValidDirection(Direction currentDirection) {


        if (this.previousDirection.equals(Direction.UP) && currentDirection.equals(Direction.DOWN)
                || this.previousDirection.equals(Direction.RIGHT) && currentDirection.equals(Direction.LEFT)
                || this.previousDirection.equals(Direction.DOWN) && currentDirection.equals(Direction.UP)
                || this.previousDirection.equals(Direction.LEFT) && currentDirection.equals(Direction.RIGHT)
        )
            return false;

        return true;
    }


    public Cell getNextPosition(Direction direction) {


        Cell newHead = snake.getLast();

        int x = newHead.getX();
        int y = newHead.getY();


        switch (direction) {

            case UP:
                x = (x - 1 + boardWidth) % boardWidth;
            case LEFT:
                y = (y - 1 + boardHeight) % boardHeight;
            case DOWN:
                x = (x + 1) % boardWidth;
            case RIGHT:
                y = (y + 1) % boardHeight;
        }


        return new Cell(x, y);
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public void render() {


        this.populateBoard();
        this.publishBoard();
        System.out.println(" Snake : " + snake);
        System.out.println(" Food Position : " + foodPosition);

    }


    public Cell generateFood() {


        while (true) {


            int x = random.nextInt(boardWidth);
            int y = random.nextInt(boardHeight);

            Cell food = new Cell(x, y);

            if (!snake.contains(food)) {

                this.foodPosition = food;
                break;
            }
        }

        return foodPosition;
    }


}
