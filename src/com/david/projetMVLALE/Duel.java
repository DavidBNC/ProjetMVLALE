package com.david.projetMVLALE;

public class Duel extends AbstractJeu {

    public Duel() {
    }

    /**
     * jouerDuel : Déroulement du jeu en mode Duel.
     */
    private void modeDuel() {
        do {
            saisie(getPropositionJoueur(), "Veuillez choisir une proposition :", "Proposition : ");
            afficherCombinaison(getPropositionJoueur());
            System.out.println(" --> Réponse : " + comparaison(getPropositionJoueur(), getCombinaisonOrdinateur()));
            jouerOrdinateur();
            System.out.print("Proposition de l'ordinateur : ");
            afficherCombinaison(getPropositionOrdinateur());
            System.out.println(" --> Réponse : " + comparaison(getPropositionOrdinateur(), getCombinaisonJoueur()));
        } while (joueurGagne() && ordinateurGagne());
        if (joueurGagne()) {
            System.out.println("L'ordinateur a gagné");
        } else if (ordinateurGagne()) {
            System.out.println("Vous avez gagné");
        }
    }

    protected void jouer() {
        System.out.println(toString());
        combinaisonOrdinateur();
        saisie(getCombinaisonJoueur(), "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        afficherCombinaison(getCombinaisonJoueur());
        System.out.println("");
        modeDuel();
    }
}
