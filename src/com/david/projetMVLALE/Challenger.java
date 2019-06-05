package com.david.projetMVLALE;

public class Challenger extends AbstractJeu {


    public Challenger(){
    }

    /**
     * jouerChall : Déroulement du jeu en mode challenger.
     */
    private void modeChallenger() {
        int compteur = 0;
        do {


            saisie(getPropositionJoueur(), "Veuillez choisir une proposition :", "Proposition : ");
            afficherCombinaison(getPropositionJoueur());
            System.out.println(" --> Réponse : " + comparaison(getPropositionJoueur(), getCombinaisonOrdinateur()));
            compteur++;
            System.out.println(compteur);
        } while (joueurGagne() || compteur < 6);
        System.out.println("Vous avez gagné.");
    }

    protected void jouer() {
        System.out.println(toString());
        combinaisonOrdinateur();
        modeChallenger();
    }
}
