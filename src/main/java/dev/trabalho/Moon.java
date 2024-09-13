package dev.trabalho;

import dev.trabalho.data.JsonMoon;

import java.util.List;

public class Moon {
    private int height, width;
    public List<Cell> cells;

    public Moon(int height, int width, List<Cell> cells){
        this.width = width;
        this.height = height;
        int moonArea= width*height;
        this.cells = cells;
    }

    public Cell getCell(Position pos){
        int index = pos.x + pos.y*height;
        if(index >= 0 && index < cells.size()) return cells.get(index);
        else return null;
    }

    public Cell getUpCell(Cell cell) {
        Position pos = cell.getPosition();
        pos.y -= 1;
        return getCell(pos);
    }

    public Cell getDownCell(Cell cell) {
        Position pos = cell.getPosition();
        pos.y += 1;
        return getCell(pos);
    }

    public Cell getLeftCell(Cell cell) {
        Position pos = cell.getPosition();
        pos.x -= 1;
        return getCell(pos);
    }

    public Cell getRightCell(Cell cell) {
        Position pos = cell.getPosition();
        pos.x += 1;
        return getCell(pos);
    }


    public int getHeight() {return this.height;}

    public int getWidth() {return this.width;}


    public static Moon readConfig(String path) {
        return JsonMoon.readFrom(path).getMoon();
    }

}
