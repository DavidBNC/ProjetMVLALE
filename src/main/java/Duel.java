package main.java;

public class Duel extends abstractJeu {

    /**
     * jouerDuel : Déroulement du jeu en mode Duel.
     */
    private void jouerDuel() {
        propositionUtilisateur();
        System.out.print("Proposition : ");
        afficherCombinaison(getPropositionUtil());
        System.out.println(" --> Réponse : " + comparaisonCombiPropo(getPropositionUtil(),getCombinaisonOrdinateur()));

        ia();
        System.out.print("Proposition : ");
        afficherCombinaison(getPropositionOrdi());
        System.out.println(" --> Réponse : " + comparaisonCombiPropo(getPropositionOrdi(), getCombinaisonSaisie()));
    }

    protected void lancerMDJ(){
        System.out.println(toString());
        combinaisonOrdinateur();
        saisieCombinaisonSecrete();
        jouerDuel();
    }
}
