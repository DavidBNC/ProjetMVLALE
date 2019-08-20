package com.david.projetMVLALE;

public class Defenseur extends AbstractJeu {

    /**
     * Déroulement du jeu en mode Défenseur.
     */

    private void modeDefenseur() {
        do {
            compteur++;
            tourOrdinateur();
            System.out.println("----------------------------");
        } while (!gagner && nbrToursMax());
        if (gagner) {
            System.out.println("L'ordinateur a gagné !!");
        }
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        saisie(combinaisonJoueur, "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        if (modeDev) {
            afficherCombinaison(combinaisonJoueur);
        } else {
            System.out.println("****");
        }
        System.out.println("");
        modeDefenseur();
    }

    protected boolean nbrToursMax() {
        if (compteur < compteurMax) {
            return true;
        } else
            System.out.println("L'ordinateur a perdu.");
        return false;
    }
}
