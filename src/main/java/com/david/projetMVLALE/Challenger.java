package com.david.projetMVLALE;

public class Challenger extends AbstractJeu {

    /**
     * Déroulement du jeu en mode challenger.
     */


    private void modeChallenger() {
        do {
            compteur++;
            tourJoueur();
            System.out.println("----------------------------");
        } while (!gagner && nbrToursMax());
        if (gagner) {
            System.out.println("Vous avez gagné !!");
        }
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        combinaisonOrdinateur(combinaisonOrdinateur);
        if (modeDev) {
            System.out.print("L'ordinateur a choisi la combinaison : ");
            afficherCombinaison(combinaisonOrdinateur);
            System.out.println("");
        }
        modeChallenger();
    }

    protected boolean nbrToursMax() {
        if (compteur < compteurMax) {
            return true;
        } else
            System.out.println("La combinaison de l'ordinateur était : ");
            afficherCombinaison(combinaisonOrdinateur);
            System.out.println("");
            System.out.println("Vous avez perdu.");
            return false;

    }
}
