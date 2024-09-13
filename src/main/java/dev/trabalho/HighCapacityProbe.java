package dev.trabalho;

public class HighCapacityProbe extends Probe {

    @Override
    public float getExtractionVolumeMultiplier() {
        if (canUse() && isActive()) {return 3.0f;}
        return super.getExtractionVolumeMultiplier();
    }

}
