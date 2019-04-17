package main.java;

public class Defenseur extends Jeu {

    public Defenseur(String number) {
        super(number);
    }

    public void modeDefenseur(){
        System.out.println("Bienvenue dans le mode Défenseur");
        saisiCombinaisonSecrete();
    }

    public void jouer(){
        modeDefenseur();
    }
}
