package fr.DocMcCoy30.EscapeGame.beans;

/**
 * Classe bean : definit les bornes maximum et minimum entre lesquelles sont generes les chiffres aleatoires
 * des combinaisons de l'ordinateur (propositions et code secret)
 */
public class Bornes {

    private int borneMin;
    private int borneMax;

    public Bornes(int borneMin, int borneMax) {
        this.borneMin = borneMin;
        this.borneMax = borneMax;
    }


    public int getBorneMin() {
        return borneMin;
    }

    public void setBorneMin(int borneMin) {
        this.borneMin = borneMin;
    }

    public int getBorneMax() {
        return borneMax;
    }

    public void setBorneMax(int borneMax) {
        this.borneMax = borneMax;
    }

}
