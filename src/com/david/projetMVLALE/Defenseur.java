package com.david.projetMVLALE;

public class Defenseur extends AbstractJeu {

    public Defenseur(){
    }

    /**
     * jouerDef : Déroulement du jeu en mode Défenseur.
     */
    private void modeDefenseur() {
        do {
            jouerOrdinateur();
            System.out.print("Proposition : ");
            afficherCombinaison(getPropositionOrdinateur());
            System.out.println(" --> Réponse : " + comparaison(getPropositionOrdinateur(), getCombinaisonJoueur()));
        } while (ordinateurGagne());
        System.out.println("L'ordinateur a gagné.");

    }

    protected void jouer() {
        System.out.println(toString());
        saisie(getCombinaisonJoueur(), "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        afficherCombinaison(getCombinaisonJoueur());
        System.out.println("");
        modeDefenseur();
    }
}
