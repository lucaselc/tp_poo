package dev.trabalho;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Squad {

    final private String name;

    final private HashMap<Robo, Float> robots;
    final private List<SimpleController> controllers;

    public Squad(String name, List<Robo> robots, List<SimpleController> controllers) {
        this.name = name;
        this.robots = robots.stream().collect(HashMap::new, (map, robo) -> map.put(robo, 0.0f), HashMap::putAll);
        this.controllers = controllers;
    }

    public String getName() {
        return name;
    }

    public Stream<Robo> getRobots() {
        return robots.keySet().stream();
    }

    public void addBarrels(Robo robo, float amount) {
        robots.put(robo, robots.get(robo) + amount);
    }

    public float getBarrelAmount() {
        return (float) robots.values().stream().mapToDouble((x) -> x).sum();
    }

    public List<SimpleController> getControllers() {
        return controllers;
    }
}
