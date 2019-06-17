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
        } while (!gagner() && nbrToursMax());
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        combinaisonOrdinateur();
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
