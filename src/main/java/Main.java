package main.java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choixMdj;
        Jeu asbtractJeu;
        System.out.println("Bienvenue dans le lanceur du jeu du Plus ou Moins.");
        System.out.println("Choisissez votre mode de jeu : 1- Challengeur, 2- Défenseur, 3- Duel)");
        do {
                choixMdj = scanner.nextInt();
            if (choixMdj == 1) {
                asbtractJeu = new Challenger();
                asbtractJeu.lancerMDJ();
            } else if (choixMdj == 2){
                asbtractJeu = new Defenseur();
                asbtractJeu.lancerMDJ();
            } else if (choixMdj == 3){
                asbtractJeu = new Duel();
                asbtractJeu.lancerMDJ();
            } else {
                System.err.println("Veuillez choisir entre les trois modes de jeu - (1 = Challengeur, 2 = Défenseur, 3 Duel)");
            }
        } while (choixMdj != 1 & choixMdj != 2 & choixMdj != 3);
    }
}
