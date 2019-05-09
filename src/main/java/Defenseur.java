package main.java;

public class Defenseur extends abstractJeu {

    /**
     * jouerDef : Déroulement du jeu en mode Défenseur.
     */
    private void jouerDef() {
        do {
            ia();
            System.out.print("Proposition : ");
            afficherCombinaison(getPropositionOrdi());
            System.out.println(" --> Réponse : " + comparaisonCombiPropo(getPropositionOrdi(), getCombinaisonSaisie()));
        } while (!comparaisonCombiPropo(getPropositionOrdi(), getCombinaisonSaisie()).equals("===="));
    }

    protected void lancerMDJ(){
        System.out.println(toString());
        saisieCombinaisonSecrete();
        jouerDef();
    }
}
