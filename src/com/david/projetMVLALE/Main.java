package com.david.projetMVLALE;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        AbstractJeu jeu = null;
        System.out.println("Bienvenue dans le lanceur du jeu du Plus ou Moins.");

        do {
            System.out.println("Choisissez votre mode de jeu : 1- Challengeur, 2- Défenseur, 3- Duel)");

            do {
                try {
                    choix = scanner.nextInt();
                } catch (InputMismatchException e) {
                    scanner.next();
                    System.err.println("Choisissez un nombre entre 1, 2 et 3");
                }
                if (choix == 1) {
                    jeu = new Challenger();
                } else if (choix == 2) {
                    jeu = new Defenseur();
                } else if (choix == 3) {
                    jeu = new Duel();
                } else {
                    System.out.println("Veuillez choisir entre les trois modes de jeu - (1 = Challengeur, 2 = Défenseur, 3 Duel)");
                }
            } while (choix < 1 || choix > 3);

            jeu.jouer();
            System.out.println("Voulez vous rejouer ? 1- Rejouer, 2- Fin du jeu");
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
            }
        } while (choix == 1);
        System.out.println("Vous avez fini de jouer.");
    }
}
