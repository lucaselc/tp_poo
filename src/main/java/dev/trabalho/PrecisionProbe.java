package dev.trabalho;

public class PrecisionProbe extends Probe{
    @Override
    public float readH3ConcentrationCell(Cell cell){
        if (canUse() && isActive()) {
                float concentration = cell.getConcentrationH3();
                concentration *= 1.0f - (RANDOM.nextFloat(cell.getErrorMin(), cell.getErrorMax())/2);
                return concentration;

        }
        return super.readH3ConcentrationCell(cell);
    }

}
