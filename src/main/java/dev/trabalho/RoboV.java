package dev.trabalho;

import static dev.trabalho.Robo.RANDOM;

public class RoboV extends Robo {

    private float errorMaxForRoboV=0.025f;

    public RoboV(Moon moon, String name, Squad squad) {
        super(moon, name, squad);
    }

    @Override
    public float getBarrelsInStorage() {
        return super.getBarrelsInStorage();
    }

    @Override
    protected float getWalkTime() {
        return super.getWalkTime();
    }

    @Override
    public float maxBarrels() {
        return super.maxBarrels();
    }

    @Override
    public Response<Float> readH3Concentration() {
        assertCanExecuteCommand();
        float concentration = cell.getConcentrationH3();
        concentration *= 1.0f - RANDOM.nextFloat(cell.getErrorMin(), errorMaxForRoboV);
        currentTimer = new Timer(2);
        return super.readH3Concentration();
    }
}
