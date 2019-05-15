package main.java;

public class Duel extends AbstractJeu {

    public Duel() {
    }

    /**
     * jouerDuel : Déroulement du jeu en mode Duel.
     */
    private void modeDuel() {
        do {
            propositionJoueur();
            System.out.print("Proposition : ");
            afficherCombinaison(getPropositionJoueur());
            System.out.println(" --> Réponse : " + comparaison(getPropositionJoueur(), getCombinaisonOrdinateur()));

            propositionOrdinateur();
            System.out.print("Proposition de l'ordinateur : ");
            afficherCombinaison(getPropositionOrdinateur());
            System.out.println(" --> Réponse : " + comparaison(getPropositionOrdinateur(), getCombinaisonJoueur()));
        } while (!comparaison(getPropositionJoueur(),getCombinaisonOrdinateur()).equals("====") &&
                !comparaison(getPropositionOrdinateur(), getCombinaisonJoueur()).equals("===="));

        if (!comparaison(getPropositionJoueur(),getCombinaisonOrdinateur()).equals("====")){
            System.out.println("L'ordinateur a gagné");
        } else if (!comparaison(getPropositionOrdinateur(), getCombinaisonJoueur()).equals("====")){
            System.out.println("Vous avez gagné");
        } else;
    }

    protected void jouer(){
        System.out.println(toString());
        combinaisonOrdinateur();
        saisieCombinaison();
        modeDuel();
    }
}
