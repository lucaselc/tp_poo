package dev.trabalho;

public class RoboXYZ extends Robo{
    public RoboXYZ(Moon moon, String name, Squad squad) {
        super(moon, name, squad);
    }


    @Override
    protected float getExtractionTime() {
        return super.getExtractionTime()*0.5f;
    }

    @Override
    protected float getWalkTime() {
        return super.getWalkTime()*0.5f;
    }

    @Override
    public float maxBarrels() {
        return super.maxBarrels()*0.5f;
    }
    @Override
    public int getUnloadTime(){return 1;}
}
