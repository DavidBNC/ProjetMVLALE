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
            saisie(propositionJoueur, "Veuillez choisir une proposition :", "Proposition : ");
            afficherCombinaison(propositionJoueur);
            System.out.println(" --> Réponse : " + comparaison(propositionJoueur, combinaisonOrdinateur));
            compteur++;
            System.out.println(compteur);
        } while (!gagner(compteur));
    }

    protected void jouer() {
        System.out.println(toString());
        combinaisonOrdinateur();
        modeChallenger();
    }
}
