package main.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choixMdj = 0;
        int choixRejouer = 0;
        AbstractJeu jeu = null;

        do {
        System.out.println("Bienvenue dans le lanceur du jeu du Plus ou Moins.");
        System.out.println("Choisissez votre mode de jeu : 1- Challengeur, 2- Défenseur, 3- Duel)");

            do {
                try {
                    choixMdj = scanner.nextInt();
                } catch (InputMismatchException e){
                    scanner.next();
                    System.err.println("Choisissez un nombre entre 1, 2 et 3");
                }
                    if (choixMdj == 1) {
                        jeu = new Challenger();
                    } else if (choixMdj == 2) {
                        jeu = new Defenseur();
                    } else if (choixMdj == 3) {
                        jeu = new Duel();
                    } else {
                        System.out.println("Veuillez choisir entre les trois modes de jeu - (1 = Challengeur, 2 = Défenseur, 3 Duel)");
                    }
                } while (choixMdj != 1 & choixMdj != 2 & choixMdj != 3);

                jeu.jouer();
                System.out.println("Voulez vous rejouer ? 1- Rejouer, 2- Fin du jeu");
            try {
                choixRejouer = scanner.nextInt();
            } catch (InputMismatchException e){
                scanner.next();
                System.err.println("Choisissez un nombre entre 1 et 2");
            }
            } while (choixRejouer != 2);
            System.out.println("Vous avez fini de jouer.");
    }
}
