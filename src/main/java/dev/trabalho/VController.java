package dev.trabalho;

import dev.trabalho.Robo.Direction;

public class VController extends SimpleController {

    private String checkWhichCellHasLessH3(Cell northCell,Cell westCell,Cell eastCell,Cell southCell){
        float smaller;
        String checkWhich="NORTH";
        smaller=northCell.getConcentrationH3();
        if (westCell.getConcentrationH3()<smaller) {
            smaller=westCell.getConcentrationH3();
            checkWhich="WEST";
        }else if (eastCell.getConcentrationH3()<smaller) {
            smaller=eastCell.getConcentrationH3();
            checkWhich="EAST";
        }else if (southCell.getConcentrationH3()<smaller) {
            smaller=southCell.getConcentrationH3();
            checkWhich="SOUTH";
        }
        return checkWhich;
    }

    private String getAdjacentCellsLow(Cell robotCurrentCell){
        Cell northCell,westCell,eastCell,southCell;
        String cellWithLess;

        northCell=moon.getUpCell(robotCurrentCell);
        westCell=moon.getLeftCell(robotCurrentCell);
        eastCell=moon.getRightCell(robotCurrentCell);
        southCell=moon.getDownCell(robotCurrentCell);

        cellWithLess=checkWhichCellHasLessH3(northCell,westCell,eastCell,southCell);
        return cellWithLess;
    }

    private String VControllerLogicLow(Robo robo){
        return getAdjacentCellsLow(robo.cell);
    }

    private String checkWhichCellHasMoreH3(Cell northCell,Cell westCell,Cell eastCell,Cell southCell){
        float bigger;
        String checkWhich="NORTH";
        bigger=northCell.getConcentrationH3();
        if (westCell.getConcentrationH3()>bigger) {
            bigger=westCell.getConcentrationH3();
            checkWhich="WEST";
        }else if (eastCell.getConcentrationH3()>bigger) {
            bigger=eastCell.getConcentrationH3();
            checkWhich="EAST";
        }else if (southCell.getConcentrationH3()>bigger) {
            bigger=southCell.getConcentrationH3();
            checkWhich="SOUTH";
        }
        return checkWhich;
    }

    private String getAdjacentCellsHigh(Cell robotCurrentCell){
        Cell northCell,westCell,eastCell,southCell;
        String cellWithMore;

        northCell=moon.getUpCell(robotCurrentCell);
        westCell=moon.getLeftCell(robotCurrentCell);
        eastCell=moon.getRightCell(robotCurrentCell);
        southCell=moon.getDownCell(robotCurrentCell);

        cellWithMore=checkWhichCellHasMoreH3(northCell,westCell,eastCell,southCell);
        return cellWithMore;
    }

    private String VControllerLogicHigh(Robo robo){
        return getAdjacentCellsHigh(robo.cell);
    }

    @Override
    public boolean takeTurn(){
        String directionToGo;
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
                    boolean r = random.nextBoolean();
                    if (r) {
                        directionToGo = VControllerLogicLow(robo);
                    }else{
                        directionToGo = VControllerLogicHigh(robo);
                    }
                    switch (directionToGo) {
                        case "NORTH":
                            break;
                        case "WEST":
                            robo.turnLeft();
                            break;
                        case "EAST":
                            robo.turnRight();
                            break;
                        case "SOUTH":
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
    
    public VController(RoboV robo){
        super(robo);
    }
}
