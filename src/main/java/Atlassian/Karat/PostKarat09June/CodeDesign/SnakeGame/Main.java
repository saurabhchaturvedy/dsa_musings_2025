package PostKarat09June.CodeDesign.SnakeGame;

public class Main {


    public static void main(String[] args) {




        SnakeGame snakeGame = new SnakeGameImpl(10,10);


        snakeGame.render();


        moveSnake(snakeGame,Direction.RIGHT,2);

        snakeGame.render();
    }

    private static void moveSnake(SnakeGame snakeGame, Direction direction,int noOfSteps) {


        for(int i=0; i<noOfSteps; i++)
        {

            snakeGame.moveSnake(direction);
        }
    }
}
