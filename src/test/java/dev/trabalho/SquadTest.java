package dev.trabalho;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
public class SquadTest {
    Squad squad = new Squad("Jupter", List.of(), List.of());
    @Test
    void getNameTest(){
        assertEquals("Jupter", squad.getName(),
        "getName não retorna a string correta");
    }

    void getRobotsTestEmpty(){
        assertEquals(List.of(), squad.getRobots().collect(Collectors.toList()),
        "getRobotsTestEmpty não retorna o valor null quando vazio");
    }

    void getControllersEmpty(){
        assertEquals(List.of(), squad.getControllers(),
        "getControllerEmpty não retorna null quando deveria em caso da lista de controllers estar vazia");
    }
    
}
