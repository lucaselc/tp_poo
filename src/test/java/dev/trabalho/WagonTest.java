package dev.trabalho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class WagonTest {
    private Wagon wagon = new Wagon(); 
    @Test
    void getWalkTimerTest(){
        assertEquals(10.0f,wagon.getWalkTimer(),
        "getWalkTimer não retorna valor esperado");
    }

    void getUnloadTimerTest(){
        assertEquals(1.0f,wagon.getUnloadTimer(),
        "getUnloadTimer não retorna valor esperado");
    }

    void getMaxBarrels(){
        assertEquals(10.0f,wagon.getMaxBarrels(),
        "getMaxBarrels não retorna valor esperado");
    }

    void getPrecisionMultiplier(){
        assertEquals(10.0f,wagon.getPrecisionMultiplier(),
        "getPrecisionMultiplier não retorna valor esperado");
    }
}
