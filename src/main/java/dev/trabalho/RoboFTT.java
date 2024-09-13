package dev.trabalho;

public class RoboFTT extends Robo {

    @Override
    public float maxBarrels() {
        return super.maxBarrels() * 3f;
    }

    @Override
    protected float getExtractionTime() {
        return super.getExtractionTime() * 2.5f;
    }

    @Override
    protected float getWalkTime() {
        return super.getWalkTime() * 1.7f;
    }

    @Override
    public int getUnloadTime() {
        return 5;
    }

    public RoboFTT(Moon moon, String name, Squad squad) {
        super(moon, name, squad);
    }

}