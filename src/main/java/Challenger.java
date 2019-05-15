package main.java;

public class Challenger extends AbstractJeu {


    public Challenger() {
    }

    /**
     * jouerChall : Déroulement du jeu en mode challenger.
     */
    private void modeChallenger() {
                do {
                propositionJoueur();
                System.out.print("Proposition : ");
                afficherCombinaison(getPropositionJoueur());
                System.out.println(" --> Réponse : " + comparaison(getPropositionJoueur(),getCombinaisonOrdinateur()));
                } while (!comparaison(getPropositionJoueur(),getCombinaisonOrdinateur()).equals("===="));
                System.out.println("Vous avez gagné.");
    }

    protected void jouer(){
        System.out.println(toString());
        combinaisonOrdinateur();
        modeChallenger();
    }
}
