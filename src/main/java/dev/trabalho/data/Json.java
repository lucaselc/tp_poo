package dev.trabalho.data;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class Json {

    static Gson gson = new Gson();
    public static <T> T readJson(String filename, Class<T> clazz) {
        try (FileReader r = new FileReader(filename)) {
            return gson.fromJson(r, clazz);
        } catch (IOException e) {
            System.out.println();
            throw new RuntimeException("Falha ao abrir arquivo " + filename, e);
        }
    }
}
