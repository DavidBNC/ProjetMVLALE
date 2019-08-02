package com.david.projetMVLALE;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scChoixMdj = new Scanner(System.in);
        Scanner scFinDeJeu = new Scanner(System.in);
        int choixMDJ = 0;
        int choixFinDeJeu = 0;
        AbstractJeu jeu = null;

        do {
            System.out.println("Bienvenue dans le lanceur du jeu du Plus ou Moins.");
            System.out.println("Choisissez votre mode de jeu : 1- Challengeur, 2- Défenseur, 3- Duel)");

            do {
                try {
                    choixMDJ = scChoixMdj.nextInt();
                } catch (InputMismatchException e) {
                    scChoixMdj.next();
                    System.err.println("Choisissez un nombre entre 1, 2 et 3");
                }
                if (choixMDJ == 1) {
                    jeu = new Challenger();
                } else if (choixMDJ == 2) {
                    jeu = new Defenseur();
                } else if (choixMDJ == 3) {
                    jeu = new Duel();
                } else {
                    System.out.println("Veuillez choisir entre les trois modes de jeu - (1 = Challengeur, 2 = Défenseur, 3 Duel)");
                }
            } while (choixMDJ < 1 || choixMDJ > 3);

            jeu.jouer();
            do {
                do {
                    choixFinDeJeu = 0;
                    System.out.println("Voulez vous rejouer ? 1- Revenir au menu, 2- Rejouer le même mode de jeu, 3- Fin du jeu");
                    try {
                        choixFinDeJeu = scFinDeJeu.nextInt();
                    } catch (InputMismatchException e) {
                        scFinDeJeu.next();
                    }
                } while (choixFinDeJeu < 1 || choixFinDeJeu > 3);
                if (choixFinDeJeu == 2) {
                    jeu.jouer();
                }
            } while (choixFinDeJeu == 2);
        } while (choixFinDeJeu == 1);
        System.out.println("Vous avez fini de jouer.");
    }
}
