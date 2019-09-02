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
            logger.info("L'utilisateur a gagné");
        }
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        combinaisonOrdinateur(combinaisonOrdinateur);
        logger.info("Combinaison ordinateur : " + afficherCombinaison(combinaisonOrdinateur));
        if (modeDev) {
            System.out.print("L'ordinateur a choisi la combinaison : " + afficherCombinaison(combinaisonOrdinateur) + "\n");
        }
        modeChallenger();
    }

    protected boolean nbrToursMax() {
        if (compteur < compteurMax) {
            return true;
        } else
            System.out.println("La combinaison de l'ordinateur était : " + afficherCombinaison(combinaisonOrdinateur) + "\n" + "Vous avez perdu.");
        logger.info("L'utilisateur a perdu");
        return false;

    }
}
