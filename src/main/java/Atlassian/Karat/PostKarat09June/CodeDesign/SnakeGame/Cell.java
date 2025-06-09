package PostKarat09June.CodeDesign.SnakeGame;

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

    public String toString() {

        return "[" + x + "," + y + "]";
    }


    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return cell.x == this.x && cell.y == this.y;
    }


    public int hashCode() {

        return Objects.hash(x, y);
    }
}
