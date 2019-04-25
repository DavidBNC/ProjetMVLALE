package main.java;

public class Defenseur extends Jeu {

    /**
     * @modeDefenseur : Methode du mode de jeu en Defenseur.
     */
    private void modeDefenseur(){
        System.out.println(toString());
        saisieManuelCombinaisonSecrete();
    }

    protected void lancerMDJ(){
        modeDefenseur();
    }
}
