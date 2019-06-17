package com.david.projetMVLALE;

public class Defenseur extends AbstractJeu {

    /**
     * jouerDef : Déroulement du jeu en mode Défenseur.
     */
    private void modeDefenseur() {
        do {
            compteur++;
            jouerOrdinateur();
            System.out.print("Proposition : ");
            afficherCombinaison(propositionOrdinateur);
            System.out.println(" --> Réponse : " + comparaison(propositionOrdinateur, combinaisonJoueur));
        } while (!gagner() && nbrToursMax());
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        saisie(combinaisonJoueur, "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        afficherCombinaison(combinaisonJoueur);
        System.out.println("");
        modeDefenseur();
    }

    protected boolean nbrToursMax() {
        if (compteur < 6) {
            return true;
        } else
            System.out.println("L'ordinateur a perdu.");
        return false;
    }
}
