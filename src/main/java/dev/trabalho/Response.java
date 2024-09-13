package dev.trabalho;

public class Response <T> {
    private Timer timer;
    private T value;

    public Response(Timer timer,T passedValue){
        this.timer = timer;
        value = passedValue;
    }

    public boolean isReady(){
        return timer.isReady();
    }

    public T getValue(){
        if (!timer.isReady()){
            return null;
        }
        return value;
    }
}
