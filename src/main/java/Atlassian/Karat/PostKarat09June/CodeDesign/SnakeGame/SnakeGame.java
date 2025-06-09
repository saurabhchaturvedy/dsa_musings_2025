package PostKarat09June.CodeDesign.SnakeGame;

public interface SnakeGame {


    void moveSnake(Direction direction);

    boolean isGameOver();

    void render();
}
