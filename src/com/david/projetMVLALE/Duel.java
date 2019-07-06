package com.david.projetMVLALE;

import java.util.Random;

public class Duel extends AbstractJeu {

    /**
     * jouerDuel : Déroulement du jeu en mode Duel.
     */
    private void modeDuel() {
        int quiCommence;
        boolean joueurGagne = false;
        boolean ordiGagne = false;
        Joueur joueur = Joueur.JOUEUR;
        Joueur ordi = Joueur.ORDINATEUR;
        Random rand = new Random();

        quiCommence = rand.nextInt(2);
        if (quiCommence == 0) {
            System.out.println(joueur.lancementDuel());
            do {
                tourJoueur();
                if (gagner) {
                    joueurGagne = true;
                    continue;
                }
                tourOrdinateur();
                if (gagner) {
                    ordiGagne = true;
                }
                System.out.println("----------------------------");
            } while (!joueurGagne && !ordiGagne);

        } else if (quiCommence == 1) {
            System.out.println(ordi.lancementDuel());
            do {
                tourOrdinateur();
                if (gagner) {
                    ordiGagne = true;
                    continue;
                }
                tourJoueur();
                if (gagner) {
                    joueurGagne = true;
                }
                System.out.println("----------------------------");
            } while (!joueurGagne && !ordiGagne);
        }
        if (joueurGagne) {
            System.out.println(joueur.toString());
        } else
            System.out.println(ordi.toString());
    }

    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        combinaisonOrdinateur(combinaisonOrdinateur);
        System.out.print("L'ordinateur a choisi la combinaison : ");
        afficherCombinaison(combinaisonOrdinateur);
        System.out.println("");
        saisie(combinaisonJoueur, "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        afficherCombinaison(combinaisonJoueur);
        System.out.println("");
        modeDuel();
    }

    protected boolean nbrToursMax() {
        return false;
    }
}
