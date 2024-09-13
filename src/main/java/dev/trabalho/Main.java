package dev.trabalho;

import dev.trabalho.data.Config;

import java.util.*;


public class Main {
    private static final PriorityQueue<ITurn> iTurnList= new PriorityQueue<ITurn>( (c1, c2) -> c2.priority() - c1.priority());
    private static final Timer remainingTime = new Timer(20);
    public static void addITurn(ITurn iTurn){
        iTurnList.add(iTurn);
        return;
    }

    public static void turn() {
        List<ITurn> copy = new LinkedList<ITurn>(iTurnList.stream().toList());
        iTurnList.clear();
        copy.removeIf(iTurn -> !iTurn.takeTurn());
        iTurnList.addAll(copy);
    }
    public static void main(String[] args) {
        Config c = Config.readConfig("config.json");
        Moon moon = Moon.readConfig("moon.json");
        List<Squad> equipes = c.getEquipes(moon);
        for(Squad eq: equipes) {
            for(SimpleController cont: eq.getControllers()) {
                addITurn(cont);
            }
            for(Robo robo: eq.getRobots()) {
                Cell cell = moon.cells.stream().filter((x) -> !x.hasRobo()).findAny().get();
                robo.spawn(cell);
                addITurn(robo);
            }
        }
        while(!remainingTime.isReady()) {
            System.out.println("Turno atual: " + remainingTime.remainingTime());
            turn();
            System.out.println("");
        }


        int i = 1;
        System.out.println("Fim de Jogo!\nPlacar:\n");
        equipes = equipes.stream().sorted( (e1, e2) -> Float.compare(e2.getBarrelAmount(), e1.getBarrelAmount())).toList();
        for(Squad equipe : equipes) {
            System.out.printf("%d. %s: %.1f barris\n", i, equipe.getName(), equipe.getBarrelAmount());
            i++;
        };
        System.out.printf("Equipe campeã: %s\n" ,equipes.get(0).getName());
    }

}
