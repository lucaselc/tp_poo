package dev.trabalho;

import dev.trabalho.Robo.Direction;

public class XYZController extends SimpleController {

    private int checkWhichCellHasLessH3(Cell northCell,Cell westCell,Cell eastCell,Cell southCell){
        float smaller;
        int checkWhich=1;
        smaller=northCell.getConcentrationH3();
        if (westCell.getConcentrationH3()<smaller) {
            smaller=westCell.getConcentrationH3();
            checkWhich=2;
        }else if (eastCell.getConcentrationH3()<smaller) {
            smaller=eastCell.getConcentrationH3();
            checkWhich=3;
        }else if (southCell.getConcentrationH3()<smaller) {
            smaller=southCell.getConcentrationH3();
            checkWhich=4;
        }
        return checkWhich;
    }

    private int getAdjacentCells(Cell robotCurrentCell){
        Cell northCell,westCell,eastCell,southCell;
        int cellWithLess;

        northCell=moon.getUpCell(robotCurrentCell);
        westCell=moon.getLeftCell(robotCurrentCell);
        eastCell=moon.getRightCell(robotCurrentCell);
        southCell=moon.getDownCell(robotCurrentCell);

        cellWithLess=checkWhichCellHasLessH3(northCell,westCell,eastCell,southCell);
        return cellWithLess;
    }

    private int XYZControllerLogic(Robo robo){
        return getAdjacentCells(robo.cell);
    }

    @Override
    public boolean takeTurn(){
        if(walk != null) {
            if(walk.isReady()) {
                Position pos = walk.getValue();
                walk = null;
                prints("chegou na célula (%d, %d)", pos.x, pos.y);
            } else {
                prints("está caminhando...");
                return true;
            }
        }
        if(prospection != null){
            if(prospection.isReady()){
                while(walk == null) {
                    robo.setDirection(Direction.Up);
                    switch (XYZControllerLogic(robo)) {
                        case 1:
                            break;
                        case 2:
                            robo.turnLeft();
                            break;
                        case 3:
                            robo.turnRight();
                            break;
                        case 4:
                        for(int i=0;i>=1;i++){
                            robo.turnLeft();
                        }
                        default:
                            break;
                    }
                    float barrels = prospection.getValue();
                    prospection = null;
                    prints("acabou de prospectar %.1f barris hélio 3!", barrels);

                    walk = robo.walk();
                    prints("irá para outra célula...");
                }
            } else {
                prints("está prospectando hélio3 ...");
            }
        }else{
            prospection =  robo.extractH3();
            prints("começou a prospecção!");
        }
        return true;
    }
    
    public XYZController(Robo robo){
        super(robo);
    }
}
