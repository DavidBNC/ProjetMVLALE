package com.david.projetMVLALE;

import java.util.Scanner;

import static com.david.projetMVLALE.AbstractJeu.logger;

public class InterfaceConsole {

    AbstractJeu jeu;

    public InterfaceConsole(AbstractJeu jeu) {
        this.jeu = jeu;
    }

    protected void jouer() {
        System.out.println(jeu.toString());
        jeu.initialisationCombi();
        Scanner scSaisie = new Scanner(System.in);
        Scanner scProposition = new Scanner(System.in);
        String strSaisie;
        String strProposition;
        int tour = 0;
        jeu.gagner = false;


        if (jeu.joueur) {
            System.out.println("Veuillez choisir votre combinaison secrète :");
            do {
                strSaisie = scSaisie.nextLine();
                if (!jeu.longeurSaisie(strSaisie) || !jeu.contenuSaisie(strSaisie)) {
                    System.err.println("Longueur ou contenu de saisie invalide. La saisie doit être de " + jeu.nbrPosition + " chiffres.");
                    logger.warn("Longueur ou contenu de saisie invalide. La saisie doit être de " + jeu.nbrPosition + " chiffres.");
                }
            } while (!jeu.longeurSaisie(strSaisie) || !jeu.contenuSaisie(strSaisie));
            jeu.saisie(jeu.combinaisonJoueur, strSaisie);

            if (jeu.modeDev) {
                System.out.println("Votre combinaison secrète est " + jeu.afficherCombinaison(jeu.combinaisonJoueur));
            }
        }
        if (jeu.ordinateur) {
            jeu.combinaisonOrdinateur(jeu.combinaisonOrdinateur);
            System.out.println("L'ordinateur choisi sa combinaison.");
            if (jeu.modeDev) {
                System.out.println("L'ordinateur a choisi la combinaison : " + jeu.afficherCombinaison(jeu.combinaisonOrdinateur));
            }
        }
        System.out.println("La partie commence !");


        do {
            if (jeu.compteurOn) {
                jeu.compteur++;
            }
            if (jeu.joueur) {
                tour = 0;
                jeu.jouerOrdinateur();
                logger.info("Proposition de l'ordinateur : " + jeu.afficherCombinaison(jeu.propositionOrdinateur));
                System.out.println("Proposition de l'ordinateur : " + jeu.afficherCombinaison(jeu.propositionOrdinateur) + " --> Réponse : " + jeu.comparaison(jeu.propositionOrdinateur, jeu.combinaisonJoueur));
                logger.info("Retour de la comparaison du tour de l'ordinateur : " + jeu.retourComparaison);
                System.out.println("----------------------------");
            }
            if (jeu.ordinateur) {
                if (jeu.gagner) {
                    continue;
                }
                tour = 1;
                System.out.println("Veuillez saisir une proposition :");
                do {
                    strProposition = scProposition.nextLine();
                    if (!jeu.longeurSaisie(strProposition) || !jeu.contenuSaisie(strProposition)) {
                        System.err.println("Longueur ou contenu de saisie invalide. La saisie doit être de " + jeu.nbrPosition + " chiffres.");
                        logger.warn("Longueur ou contenu de saisie invalide. La saisie doit être de " + jeu.nbrPosition + " chiffres.");
                    }
                } while (!jeu.longeurSaisie(strProposition) || !jeu.contenuSaisie(strProposition));
                jeu.saisie(jeu.propositionJoueur, strProposition);
                logger.info("Proposition de l'utilisateur : " + jeu.afficherCombinaison(jeu.propositionJoueur));
                System.out.println("Votre proposition : " + jeu.afficherCombinaison(jeu.propositionJoueur) + " --> Réponse : " + jeu.comparaison(jeu.propositionJoueur, jeu.combinaisonOrdinateur));
                logger.info("Retour de la comparaison du tour de l'utilisateur : " + jeu.retourComparaison);
                System.out.println("----------------------------");
            }
        } while (!jeu.gagner && jeu.nbrToursMax());


        if (jeu.gagner) {
            if (tour == 0) {
                System.out.println("L'ordinateur a gagné !");
                logger.info("L'ordinateur a gagné");
            } else {
                System.out.println("Vous avez gagné !");
                logger.info("Vous avez gagné");
            }
        }
        if (!jeu.nbrToursMax() && !jeu.gagner) {
            if (tour == 0) {
                System.out.println("L'ordinateur a perdu, la combinaison correcte était " + jeu.afficherCombinaison(jeu.combinaisonJoueur) + ".");
                logger.info("L'ordinateur a perdu, la combinaison correcte était " + jeu.afficherCombinaison(jeu.combinaisonJoueur) + ".");
            } else {
                System.out.println("Vous avez perdu, la combinaison correcte de l'ordinateur était " + jeu.afficherCombinaison(jeu.combinaisonOrdinateur) + ".");
                logger.info("Vous avez perdu, la combinaison correcte de l'ordinateur était " + jeu.afficherCombinaison(jeu.combinaisonOrdinateur) + ".");
            }
        }
    }
}

