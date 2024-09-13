package dev.trabalho;

public class Position implements Cloneable {
    public int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Position clone() {
        return new Position(this.x, this.y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }


}
