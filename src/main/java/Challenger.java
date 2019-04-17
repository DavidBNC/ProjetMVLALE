package main.java;

import java.util.Random;

public class Challenger extends Jeu {

    public Challenger(String number) {
        super(number);
    }

    public void modeChallenger(){
        Random r = new Random();
        int cs1 = r.nextInt(9);
        int cs2 = r.nextInt(9);
        int cs3 = r.nextInt(9);
        int cs4 = r.nextInt(9);
        String combi1 = Integer.toString(cs1);
        String combi2 = Integer.toString(cs2);
        String combi3 = Integer.toString(cs3);
        String combi4 = Integer.toString(cs4);
        String combiSecrete = combi1 + combi2 + combi3 + combi4;
        setNumber(combiSecrete);

        System.out.println("Bienvenue dans le mode Challenger");
        System.out.println("L'ordinateur a choisi la combinaison : " + getNumber());
        afficherChar();
    }

    public void jouer(){
        modeChallenger();
    }
}
