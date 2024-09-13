package dev.trabalho;
public class Timer implements ITurn {
    private int counter;
    final private int initialTime;
    public Timer(int counter){
        this.counter = counter;
        initialTime = counter;
        Main.addITurn(this);
    }
    public int timeSinceStart() {
        return initialTime - counter;
    }

    public int remainingTime() {
        return counter;
    }
    @Override
    public boolean takeTurn(){
        if(counter > 0) {
            counter--;
        }
        return counter > 0;
    }

    @Override
    public int priority() {
        return 15;
    }

    public boolean isReady(){
        return counter == 0;
    }


}
