package com.david.projetMVLALE;

import java.util.Random;

public class Duel extends AbstractJeu {

    /**
     * Déroulement du jeu en mode Duel.
     */
    private void modeDuel() {
        int quiCommence;
        boolean joueurGagne = false;
        boolean ordiGagne = false;

        Random rand = new Random();

        quiCommence = rand.nextInt(2);
        if (quiCommence == 0) {

            System.out.println("Tu commences à jouer.");
            logger.info("L'utilisateur commence à jouer.");
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
            System.out.println("L'ordinateur commence à jouer.");
            logger.info("L'ordinateur commence à jouer.");
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
            System.out.println("Vous avez gagné la partie.");
            logger.info("L'utilisateur a gagné la partie.");

        } else
            System.out.println("L'ordinateur a gagné la partie.");
        logger.info("L'ordinateur a gagné la partie.");

    }


    protected void jouer() {
        System.out.println(toString());
        initialisationCombi();
        combinaisonOrdinateur(combinaisonOrdinateur);
        logger.info("Combinaison ordinateur : " + afficherCombinaison(combinaisonOrdinateur));
        if (modeDev) {
            System.out.print("L'ordinateur a choisi la combinaison : " + afficherCombinaison(combinaisonOrdinateur) + "\n");
        }
        saisie(combinaisonJoueur, "Veuillez entrer votre combinaison secrète : ", "Combinaison secrète : ");
        logger.info("Combinaison joueur : " + afficherCombinaison(combinaisonJoueur));
        if (modeDev) {
            System.out.println( afficherCombinaison(combinaisonJoueur) + "\n");
        } else
            System.out.println("****");
        modeDuel();
    }

    protected boolean nbrToursMax() {
        return false;
    }
}
