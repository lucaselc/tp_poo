package dev.trabalho;

import java.util.Random;
public class SimpleController implements ITurn {
    protected Robo robo;
    protected Moon moon;
    final static protected Random random = new Random();
    protected Response<Float> prospection;
    protected Response<Position> walk;

    protected void prints(String format, Object... args) {
        System.out.printf("Robo %s ", robo.getName());
        System.out.printf(format + "\n", args);
    }

    @Override
    public boolean takeTurn() {
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
                    boolean r = random.nextBoolean();
                    if(r) {
                        robo.turnLeft();
                    }else{
                        robo.turnRight();
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

    @Override
    public int priority() {
        return 5;
    }


    public SimpleController(Robo robo) {
        this.robo = robo;
    }

}
