package dev.trabalho.data;

import dev.trabalho.Moon;
import dev.trabalho.Robo;
import dev.trabalho.SimpleController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Config {

    private class ExceptionSimpleControllerExpected extends RuntimeException {

        @Override
        public String getMessage() {
            return "A única classe de Controlador é a SimpleController";
        }
    }

    private Equipe[] equipes;
    public static class Equipe {
        public String name;
        public HashMap<String, String> robos;
    }

    private SimpleController createController(String controllerName,Robo robo) {
        if (controllerName.equals("SimpleController")) {
            return new SimpleController(robo);
        }else{
            throw new ExceptionSimpleControllerExpected();
        }
    }

    public List<dev.trabalho.Squad> getEquipes(Moon moon) {
        return Arrays.stream(this.equipes).map( (eq) -> {
            List<Robo> robots = (eq.robos).keySet().stream().map((rob) -> new Robo(moon, rob)).toList();
            List<SimpleController> controllers = robots.stream().map((robo) -> createController(eq.robos.get(robo.getName()), robo)).toList();
            return new dev.trabalho.Squad(eq.name, robots, controllers);
        }).toList();
    }
    public static Config readConfig(String filename) {
        return Json.readJson(filename, Config.class);
    }
}
