package main.java;

import java.util.Scanner;

public class Challenger extends Jeu {

    /**
     * modeChallenger : Methode du mode de jeu en Challenger.
     */
    private void modeChallenger(){
        System.out.println(toString());
        combinaisonOrdinateur();
        jouerChall();
    }

    /**
     * jouerChall : Déroulement du jeu en mode challenger.
     */
    private void jouerChall(){
        proposition();
        System.out.println("Proposition : " + getProposition()[0] + "" + getProposition()[1] + "" + getProposition()[2]
                + "" + getProposition()[3] + " --> Proposition : " + comparaisonChar());
        }

    /**
     * comparaisonChar : Renvoi la comparaison des caractères de la proposition.
     * @return
     */
    public String comparaisonChar() {

        for (int i = 0; i <= 3; i++) {
            if (getProposition()[i] < getCombinaisonOrdinateur()[i]) {
                return "+";
            } else if (getProposition()[i] > getCombinaisonOrdinateur()[i]) {
                return "-";
            } else {
                return "=";
        }
        }
        return null;
    }

    protected void lancerMDJ(){
        modeChallenger();
    }
}
