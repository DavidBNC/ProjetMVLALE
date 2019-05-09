package main.java;

public class Challenger extends abstractJeu {


    /**
     * jouerChall : Déroulement du jeu en mode challenger.
     */
    private void jouerChall() {
                do {
                propositionUtilisateur();
                System.out.print("Proposition : ");
                afficherCombinaison(getPropositionUtil());
                System.out.println(" --> Réponse : " + comparaisonCombiPropo(getPropositionUtil(),getCombinaisonOrdinateur()));
                } while (!comparaisonCombiPropo(getPropositionUtil(),getCombinaisonOrdinateur()).equals("===="));
                System.out.println("Vous avez gagné.");
    }

    protected void lancerMDJ(){
        System.out.println(toString());
        combinaisonOrdinateur();
        jouerChall();
    }
}
