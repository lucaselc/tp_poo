package dev.trabalho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;

class CellTest {
    private Robo roboInCell=null;
    Position position = new Position(2, 3);
    Cell cell = new Cell(0.8f,0.4f,0.0f,0.1f,position);
    @Test
    void enterTest(Robo r){
        assertEquals(roboInCell, r,
        "Não recebeu o Robo esperado em enter");
    }

    void exitTest(){
        assertNull(roboInCell,
        "roboInCell deveria apresentar valor null, porém não apresenta");
    }

    void getPositionTest(){
        assertEquals(position, cell.getPosition(),
        "get possition não retorna um clone de possição como deveria");
    }

    void hasRobotTest(){
        assertNotNull(cell.hasRobo(),
        "hasRobo deveria retornar not null mas retorna null");
    }

    void getErrorMinTest(){
        assertEquals(0, cell.getErrorMin(),
        "getErrorMin não retorna o valor que deveria");
    }

    void getErrorMaxTest(){
        assertEquals(0, cell.getErrorMax(),
        "getErrorMax não retorna o valor que deveria");
    }
    
} 
