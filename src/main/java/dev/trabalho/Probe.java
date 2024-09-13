package dev.trabalho;

import java.util.Random;

public abstract class Probe {
    protected int remainingUses;
    static protected final Random RANDOM = new Random();

    private boolean active = false;
    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
    }
    public boolean canUse() {
        return active && remainingUses > 0;
    }

    public void use() {
        remainingUses--;
    }

    public float getExtractionTimeMultiplier() {
        return 1.0f;
    }
    public float getExtractionVolumeMultiplier() {
        return 1.0f;
    }

    public float readH3ConcentrationCell(Cell cell){
        float concentration = cell.getConcentrationH3();
        concentration *= 1.0f - RANDOM.nextFloat(cell.getErrorMin(), cell.getErrorMax());
        return concentration;
    }

}
