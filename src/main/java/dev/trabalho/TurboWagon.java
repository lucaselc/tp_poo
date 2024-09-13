package dev.trabalho;

public class TurboWagon extends Wagon{
    @Override
    public float getWalkTimer(){return super.getWalkTimer()*0.8f;}
    @Override
    public int getUnloadTimer(){return super.getUnloadTimer()/2;}

}
