package Post14June.Design.SnakeGame;

public class Main {


    public static void main(String[] args) {


        SnakeGame snakeGame = new SnakeGameImpl(10, 10);


        snakeGame.render();

        moveSnake(3,Direction.RIGHT,snakeGame);

        snakeGame.render();

        moveSnake(5,Direction.RIGHT,snakeGame);

        snakeGame.render();
    }


    public static void moveSnake(Integer moves, Direction direction, SnakeGame snakeGame) {

        for (int i = 0; i < moves; i++) {

            snakeGame.moveSnake(direction);
        }
    }
}
