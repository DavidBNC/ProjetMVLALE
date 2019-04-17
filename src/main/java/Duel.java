package main.java;

public class Duel extends Jeu {

    public Duel(String number) {
        super(number);
    }

    public void modeDuel(){
        System.out.println("Bienvenue dans le mode Duel");
        saisiCombinaisonSecrete();
    }

    public void jouer(){
        modeDuel();
    }
}
