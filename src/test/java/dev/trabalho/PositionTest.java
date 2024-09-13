package dev.trabalho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class PositionTest{

    private Position position = new Position(2,3);

    @Test
    void constructTestX(){
        assertEquals(position.x, 2);
    }

    @Test
    void constructTestY(){
        assertEquals(position.y, 3);
    }

    @Test
    void cloneMethodTest(){
        assertNotNull(position.clone(),
        "Retorna valor nullo, o que está errado uma vez que era esperado uma nova posição");
    }

    @Test
    void toStingTest(){
        assertEquals(String.format("(%d, %d)", position.x, position.y),position.toString(),
        "Função toString de position não retorna a string que deveria");
    }
}
