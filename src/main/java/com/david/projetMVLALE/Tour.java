package com.david.projetMVLALE;

public enum Tour {

    JOUEUR("Tu as", "Tu commences"),
    ORDINATEUR("L'ordinateur a","L'ordinateur commence");

    private String debutPhrase = "";
    private String debutDuel = "";

    Tour (String debutPhrase, String debutDuel) {
        this.debutPhrase = debutPhrase;
        this.debutDuel = debutDuel;
    }

    public String toString(){
        return debutPhrase + " gagné la partie !";
}

    public String lancementDuel(){ return debutDuel + " à jouer.";}
}
