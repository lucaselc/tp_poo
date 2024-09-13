package dev.trabalho;

public class FastProbe extends Probe{
    @Override
    public float getExtractionTimeMultiplier() {
        if (canUse() && isActive()) {
            if (remainingUses == 2) {
                return 0.5f;
            }
            if (remainingUses == 1){return 0.0f;}
        }
        return super.getExtractionVolumeMultiplier();
    }
}
