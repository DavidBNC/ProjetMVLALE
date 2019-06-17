package com.david.projetMVLALE;

public class Duel extends AbstractJeu {

    /**
     * jouerDuel : Déroulement du jeu en mode Duel.
     */
    private void modeDuel() {
        do {
            saisie(propositionJoueur, "Veuillez choisir une proposition :", "Proposition : ");
            afficherCombinaison(propositionJoueur);
            System.out.println(" --> Réponse : " + comparaison(propositionJoueur, combinaisonOrdinateur));
            jouerOrdinateur();
            System.out.print("Proposition de l'ordinateur : ");
            afficherCombinaison(propositionOrdinateur);
            System.out.println(" --> Réponse : " + comparaison(propositionOrdinateur, combinaisonJoueur));
        } while (!gagner());
    }

    protected void jouer() {
        System.out.println(toString());
        combinaisonOrdinateur();
        saisie(combinaisonJoueur, "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        afficherCombinaison(combinaisonJoueur);
        System.out.println("");
        modeDuel();
    }

    protected boolean nbrToursMax() {
        return false;
    }
}
