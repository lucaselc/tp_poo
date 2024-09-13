package dev.trabalho;

public class AdvancedNavigationWagon extends Wagon{
    @Override
    public float getWalkTimer(){return super.getWalkTimer()*0.75f;}

    @Override
    public float getPrecisionMultiplier() {return super.getPrecisionMultiplier()*1.3f;}

}
