package main.java;

public class Defenseur extends Jeu {

    /**
     * modeDefenseur : Methode du mode de jeu en Defenseur.
     */
    private void modeDefenseur(){
        System.out.println(toString());
        saisieCombinaisonSecrete();
        proposition();
    }

    protected void lancerMDJ(){
        modeDefenseur();
    }
}
