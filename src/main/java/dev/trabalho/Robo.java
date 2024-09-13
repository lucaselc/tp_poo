package dev.trabalho;

import java.util.Random;

public abstract class Robo implements ITurn {

    final private String name;
    protected Cell cell;
    private float h3Barrels = 0;
    private Direction direction=Direction.Up;
    protected Timer currentTimer = null;
    final private Moon moon;
    protected Probe probe;
    protected Wagon wagon;
    private Squad squad;

    static protected final Random RANDOM = new Random();

    protected Robo(Moon moon, String name, Squad squad){
        direction=Direction.Up;
        this.moon = moon;
        this.name = name;
        this.squad = squad;
    }


    public float maxBarrels() {
        return wagon.getMaxBarrels();
    }

    protected float getExtractionTime() {
        return 5.0f * probe.getExtractionTimeMultiplier();
    }

    protected float getWalkTime() {
        return wagon.getWalkTimer();
    }
    protected float getPrecisionMultiplier(){ return wagon.getPrecisionMultiplier();}

    @Override
    public boolean takeTurn(){
        if(currentTimer != null && currentTimer.isReady()){
            currentTimer = null;
        }
        return true;
    }

    public void spawn(Cell cell) {
        this.cell = cell;
        cell.enter(this);
    }

    @Override
    public int priority() {
        return 10;
    }
    static enum Direction {
        Up,
        Left,
        Right,
        Down
    }

    public int getTimeSinceCommandStart() {
        return currentTimer.timeSinceStart();
    }

    public boolean isExecutingCommand() {
        return currentTimer != null;
    }

    protected void assertCanExecuteCommand() {
        if(isExecutingCommand()) {
            throw new IllegalStateException("Tried to give a command but robot is already executing one!");
        }
    }

    public String getName() {
        return name;
    }

    public Response<Float> readRoughness() {
        float roughness;
        assertCanExecuteCommand();
        Cell c = getFrontCell();
        if (c == null) {
            return null;
        }
        else roughness = c.getRoughness();
        currentTimer =  new Timer(1);
        return new Response<Float>(currentTimer, roughness);
    }

    public Response<Float> readH3Concentration(){
        currentTimer = new Timer(2 );
        return new Response<Float>(currentTimer, probe.readH3ConcentrationCell(cell));
    }

    public float getBarrelsInStorage() {
        return h3Barrels;
    }

    public Response<Float> extractH3(){
        assertCanExecuteCommand();
        float concentration = cell.getConcentrationH3()*probe.getExtractionVolumeMultiplier();
        float barrelCount = cell.extractBarrels();
        h3Barrels += barrelCount;
        currentTimer = new Timer((int)(concentration*getExtractionTime()));
        return new Response<Float>(currentTimer, barrelCount);
    }

    public Response<Float> unload(){
        assertCanExecuteCommand();
        float barrels = h3Barrels;
        h3Barrels = 0.0f;
        currentTimer = new Timer(getUnloadTime());
        squad.addBarrels(this, barrels);
        return new Response<Float>(currentTimer, barrels);
    }
    public int getUnloadTime(){return wagon.getUnloadTimer();}
    private Cell getFrontCell() {
        return switch (this.direction) {
            case Up -> moon.getUpCell(cell);
            case Down -> moon.getDownCell(cell);
            case Left -> moon.getLeftCell(cell);
            case Right -> moon.getRightCell(cell);
        };
    }

    public Response<Position> walk(){
        assertCanExecuteCommand();
        Cell nextcell = getFrontCell();
        Timer timer;
        if(nextcell != null && !nextcell.hasRobo()){
            timer = this.currentTimer = new Timer((int) (nextcell.getRoughness() * getWalkTime()));
            cell.exit(this);
            cell = nextcell;
        } else {
            timer = new Timer(0);
        }

        return new Response<Position>(timer, cell.getPosition());
    }

    public void turnLeft(){
        assertCanExecuteCommand();
        switch(direction){
            case Up:
                direction = Direction.Left;
            case Down:
                direction = Direction.Right;
            case Left:
                direction = Direction.Down;
            case Right:
                direction = Direction.Up;
        }

    }

    public void turnRight(){
        assertCanExecuteCommand();
        switch(direction){
            case Up:
                direction = Direction.Right;
            case Left:
                direction = Direction.Up;
            case Down:
                direction = Direction.Left;
            case Right:
                direction = Direction.Down;
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
