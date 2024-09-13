package dev.trabalho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class RoboXYZTest {
    RoboXYZ roboXYZ;
    @Test
    void getExtractionTimeTest(){
        assertEquals(0.5f,roboXYZ.getExtractionTime(),
        "Não foi retornado o valor correto em getExtrationTimeTest");
    }

    void getWalkTimerTest(){
        assertEquals(0.5f,roboXYZ.getWalkTime(),
        "Não foi retornado o valor correto em getWalkTimeTest");
    }
    
     void maxBarrelsTest(){
        assertEquals(0.5f,roboXYZ.maxBarrels(),
        "Não foi retornado o valor correto em maxBarrelsTest");
    }

    void getUnloadTime(){
        assertEquals(0.5f,roboXYZ.getUnloadTime(),
        "Não foi retornado o valor correto em getUnloadTimeTest");
    }
}
