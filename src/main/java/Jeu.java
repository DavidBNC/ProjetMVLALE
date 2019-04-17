package main.java;

import java.util.Scanner;

public abstract class Jeu {

    private String number;
    byte[] stockageCS;

    public Jeu(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void saisiCombinaisonSecrete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre combinaison secrète :");
        setNumber(scanner.nextLine());
        System.out.println("Votre combinaison est " + getNumber());
        afficherChar();

    }

    public void afficherChar(){
        for (int i = 0; i < getNumber().length(); i++) {
            System.out.println(i + " - " + getNumber().charAt(i));
        }

    }
    public abstract void jouer();
}
