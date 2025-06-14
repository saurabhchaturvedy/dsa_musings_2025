package Post14June.Design.SnakeGame;

public class MainThread {


    public static void main(String[] args) throws InterruptedException {



        SnakeGameImpl game = new SnakeGameImpl(10, 10);
        game.start(); // Starts automatic movement

        // Simulate direction changes from UI, user, etc.
        Thread.sleep(5000);
        game.setDirection(Direction.DOWN);

        Thread.sleep(5000);
        game.setDirection(Direction.LEFT);


    }
}
