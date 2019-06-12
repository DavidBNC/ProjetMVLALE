package com.david.projetMVLALE;

public class Defenseur extends AbstractJeu {

    public Defenseur(){
    }

    /**
     * jouerDef : Déroulement du jeu en mode Défenseur.
     */
    private void modeDefenseur() {
        int compteur = 0;
        do {
            jouerOrdinateur();
            System.out.print("Proposition : ");
            afficherCombinaison(propositionOrdinateur);
            System.out.println(" --> Réponse : " + comparaison(propositionOrdinateur, combinaisonJoueur));
        } while (!gagner(compteur));
    }

    protected void jouer() {
        System.out.println(toString());
        saisie(combinaisonJoueur, "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        afficherCombinaison(combinaisonJoueur);
        System.out.println("");
        modeDefenseur();
    }
}
