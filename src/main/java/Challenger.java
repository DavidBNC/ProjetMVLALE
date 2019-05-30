package main.java;

public class Challenger extends AbstractJeu {


    public Challenger() {
    }

    /**
     * jouerChall : Déroulement du jeu en mode challenger.
     */
    private void modeChallenger() {
        do {
            saisie(getPropositionJoueur(), "Veuillez choisir une proposition :", "Proposition : ");
            afficherCombinaison(getPropositionJoueur());
            System.out.println(" --> Réponse : " + comparaison(getPropositionJoueur(), getCombinaisonOrdinateur()));
        } while (joueurGagne());
        System.out.println("Vous avez gagné.");
    }

    protected void jouer() {
        System.out.println(toString());
        combinaisonOrdinateur();
        modeChallenger();
    }
}
