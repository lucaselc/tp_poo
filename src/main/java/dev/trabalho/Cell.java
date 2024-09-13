package dev.trabalho;

public class Cell {

    private Position position;
    transient private Robo roboInCell = null;
    private float concentrationH3;
    private float errorMin = 0.0f;
    private float errorMax = 0.1f;
    private final float roughness;

    public Cell(float concentrationH3, float roughness, float errorMin, float errorMax, Position position) {
        this.concentrationH3 = concentrationH3;
        this.roughness = roughness;
        this.errorMin = errorMin;
        this.errorMax = errorMax;
        this.position = position;
    }

    public void enter(Robo r) {
        roboInCell = r;
    }

    public void exit(Robo r) {
        if (roboInCell == r) {
            roboInCell = null;
        }
    }

    public Position getPosition() {
        return this.position.clone();
    }

    public float getConcentrationH3() {
        return concentrationH3;
    }

    public boolean hasRobo() {
        return roboInCell != null;
    }

    public float getRoughness() {
        return roughness;
    }

    public float getErrorMin() {
        return errorMin;
    }

    public float getErrorMax() {
        return errorMax;
    }

    public float extractBarrels() {
        float numberOfBarrelsExtract = extractBarrelsformula();
        concentrationH3 = 0;
        return numberOfBarrelsExtract;
    }

    private float extractBarrelsformula(){
        float numberOfBarrelsExtract=concentrationH3*15;
        return numberOfBarrelsExtract;

    }
}
