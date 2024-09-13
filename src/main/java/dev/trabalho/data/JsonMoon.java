package dev.trabalho.data;

import dev.trabalho.Cell;
import dev.trabalho.Moon;

import java.util.List;

public class JsonMoon {
    public int height;
    public int width;

    public List<Cell> cells;

    public static JsonMoon readFrom(String filename) {
        return Json.readJson(filename, JsonMoon.class);
    }

    public Moon getMoon() {
        return new Moon(height, width, cells);
    }

}
