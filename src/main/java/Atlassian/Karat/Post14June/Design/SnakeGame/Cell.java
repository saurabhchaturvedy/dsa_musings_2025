package Post14June.Design.SnakeGame;

import java.util.Objects;

public class Cell {


    int x;
    int y;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean equals(Object o) {

        if (o == this) return true;
        if (o instanceof Cell && o.getClass() != getClass()) return false;
        Cell cell = (Cell) o;

        return this.x == cell.x && this.y == cell.y;
    }


    public int hashCode() {

        return Objects.hash(x, y);
    }


    public String toString() {

        return "[" + x + "," + y + "]";
    }
}
