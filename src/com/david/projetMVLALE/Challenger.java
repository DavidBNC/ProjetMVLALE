package com.david.projetMVLALE;

public class Challenger extends AbstractJeu {

    /**
     * jouerChall : Déroulement du jeu en mode challenger.
     */
    private void modeChallenger() {
        do {
            compteur++;
            saisie(propositionJoueur, "Veuillez choisir une proposition :", "Proposition : ");
            afficherCombinaison(propositionJoueur);
            System.out.println(" --> Réponse : " + comparaison(propositionJoueur, combinaisonOrdinateur));
        } while (!gagner && nbrToursMax());
        if (gagner) {
            System.out.println("Vous avez gagné !!");
        }
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        combinaisonOrdinateur(combinaisonOrdinateur);
        System.out.print("L'ordinateur a choisi la combinaison : ");
        afficherCombinaison(combinaisonOrdinateur);
        System.out.println("");
        modeChallenger();
    }

    protected boolean nbrToursMax() {
        if (compteur < 6) {
            return true;
        } else
            System.out.println("Vous avez perdu.");
        return false;
    }
}
