package main.java;

public class Duel extends Jeu {

    /**
     * @modeDuel : Methode du mode de jeu en Duel.
     */
    private void modeDuel(){
        System.out.println(toString());
        saisieManuelCombinaisonSecrete();
        combinaisonSecreteOrdinateur();
    }

    protected void lancerMDJ(){
        modeDuel();
    }
}
