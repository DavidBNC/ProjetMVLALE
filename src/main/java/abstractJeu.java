package main.java;

import java.util.Random;
import java.util.Scanner;

public abstract class abstractJeu{

    private byte[] combinaisonSaisie = new byte[4];
    private byte[] combinaisonOrdinateur = new byte[4];
    private byte[] propositionUtil = new byte[4];
    private byte[] propositionOrdi = new byte[4];
    private char[] iA = new char[4];

    public abstractJeu(){
    }

    /**
     * saisieCombinaisonSecrete : Saisie manuel de l'utilisateur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void saisieCombinaisonSecrete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre combinaison secrète :");
        String saisiCombi = scanner.nextLine();
        saisiePropoCombiUtil(saisiCombi, combinaisonSaisie);
        System.out.print("Combinaison secrète : ");
        afficherCombinaison(combinaisonSaisie);
        System.out.println("");
        }

    /**
     * combinaisonOrdinateur : Génère la combinaison secrète de l'ordinateur en passant par différentes conversions +
     * Stock dans un tableau de byte les caractères un à un de la combinaison secrète.
     */
    protected void combinaisonOrdinateur(){
        saisiePropoCombiOrdi(combinaisonOrdinateur);
        System.out.print("L'ordinateur a choisi la combinaison : ");
        afficherCombinaison(combinaisonOrdinateur);
        System.out.println("");
    }

    /**
     * propositionUtilisateur : Génère la proposition qui doit être comparé aux combinaisons.
     */

    protected void propositionUtilisateur(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir une proposition :");
        String propositionStr = scanner.nextLine();
        saisiePropoCombiUtil(propositionStr, propositionUtil);
    }

    /**
     * propositionOrdinateur : Génère la proposition qui doit être comparé aux combinaisons + intelligence artificielle.
     */

    protected void propositionOrdinateur(){
        saisiePropoCombiOrdi(propositionOrdi);
        }

    /**
     * saisiePropoCombi : Factorisation de la saisie Utilisateur pour la saisie de la combinaison + proposition.
     * @param saisiCombi
     * @param combinaisonSaisie
     */
    private void saisiePropoCombiUtil(String saisiCombi, byte[] combinaisonSaisie){

        for (int i = 0; i <= 3; i++){
            char caracSaisie = saisiCombi.charAt(i);
            byte caracSaisieByte = (byte)(caracSaisie - 48);
            combinaisonSaisie[i] = caracSaisieByte;
        }
    }

    /**
     * saisiePropoCombi : Factorisation de la saisie Utilisateur pour la saisie de la combinaison + proposition.
     * @param combinaisonSaisie
     */
    private void saisiePropoCombiOrdi(byte[] combinaisonSaisie){
        Random rdmCombi = new Random();

        for (int i = 0; i <= 3; i++) {
            int chiffrePropoOrdi = rdmCombi.nextInt(10);
            byte chiffreByte = (byte) (chiffrePropoOrdi);
            combinaisonSaisie[i] = chiffreByte;
        }
    }

    /**
     * comparaisonCombiPropo : Renvoi la comparaison de chaque caractères de la proposition.
     * @return
     */
    public String comparaisonCombiPropo(byte[] propo,byte[] combi) {
        String str = "";
        String ajout;
        for (int i = 0; i <= 3; i++) {
            if (propo[i] < combi[i]) {
                ajout = "+";
                str = str + ajout;
            } else if (propo[i] > combi[i]) {
                ajout = "-";
                str = str + ajout;
            } else {
                ajout = "=";
                str = str + ajout;
            }
        }
        return str;
    }

    /**
     *
     */
    public void ia() {
        Random rdmIA = new Random();
        for (int i = 0; i <= 3; i++) {
            char charIA = comparaisonCombiPropo(propositionOrdi, combinaisonSaisie).charAt(i);
            iA[i] = charIA;
            if (iA[i] == '+') {
                if (combinaisonSaisie[i] == propositionOrdi[i]){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 0) {
                    int propoOrdi = 1 + rdmIA.nextInt(9);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 1) {
                    int propoOrdi = 2 + rdmIA.nextInt(8);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 2) {
                    int propoOrdi = 3 + rdmIA.nextInt(7);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 3) {
                    int propoOrdi = 4 + rdmIA.nextInt(6);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 4) {
                    int propoOrdi = 5 + rdmIA.nextInt(5);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 5) {
                    int propoOrdi = 6 + rdmIA.nextInt(4);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 6) {
                    int propoOrdi = 7 + rdmIA.nextInt(3);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 7) {
                    int propoOrdi = 8 + rdmIA.nextInt(2);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 8) {
                    int propoOrdi = 9 + rdmIA.nextInt(1);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else ;
            } else if (iA[i] == '-') {
                if (combinaisonSaisie[i] == propositionOrdi[i]) {
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 9) {
                    int propoOrdi = rdmIA.nextInt(9);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 8) {
                    int propoOrdi = rdmIA.nextInt(8);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 7) {
                    int propoOrdi = rdmIA.nextInt(7);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 6) {
                    int propoOrdi = rdmIA.nextInt(6);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 5) {
                    int propoOrdi = rdmIA.nextInt(5);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 4) {
                    int propoOrdi = rdmIA.nextInt(4);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 3) {
                    int propoOrdi = rdmIA.nextInt(3);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 2) {
                    int propoOrdi = rdmIA.nextInt(2);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else if (combinaisonSaisie[i] == 1) {
                    int propoOrdi = rdmIA.nextInt(1);
                    byte propoOrdiByte = (byte) (propoOrdi);
                    propositionOrdi[i] = propoOrdiByte;
                } else;
            } else if (iA[i] == '='){
                if (combinaisonSaisie[i] == 0){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 1){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 2){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 3){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 4){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 5){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 6){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 7){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 8){
                    propositionOrdi[i] = propositionOrdi[i];
                } else if (combinaisonSaisie[i] == 9){
                    propositionOrdi[i] = propositionOrdi[i];
                } else;
            }
        }
    }

    /**
     * afficherCombinaison : Affiche la combinaison.
     * @param combi
     */
    protected void afficherCombinaison(byte[] combi){

        for (int i = 0; i <= 3; i++) {
            System.out.print(combi[i]);
        }
    }

    /**
     * lancerMDJ : Methode pour lancer les différents mode de jeu dans le main.
     */
    protected abstract void lancerMDJ();

    /**
     * toString : Envoi le message de bienvenue a chaque début de partie.
     */
    public String toString(){
        return "Bienvenue dans le mode " + getClass().getSimpleName();
    }

    public byte[] getCombinaisonSaisie() {
        return combinaisonSaisie;
    }

    public byte[] getCombinaisonOrdinateur() {
        return combinaisonOrdinateur;
    }

    public byte[] getPropositionUtil() {
        return propositionUtil;
    }

    public byte[] getPropositionOrdi() {
        return propositionOrdi;
    }
}
