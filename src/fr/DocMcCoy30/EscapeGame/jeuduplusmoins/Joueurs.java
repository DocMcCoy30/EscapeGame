package fr.DocMcCoy30.EscapeGame.jeuduplusmoins;

import fr.DocMcCoy30.EscapeGame.beans.Configuration;

import java.util.ArrayList;

/**
 * Definit les methodes et attributs communs aux joueurs (JoueurHumain et JoueurOrdi)
 */
public abstract class Joueurs {

    protected Configuration config;
    protected ArrayList<Character> tabIndice = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput = new ArrayList<>();
    protected ArrayList<String> tabCombiDejaJouees = new ArrayList<>();
    protected ArrayList<String> tabIndicesDejaJoues = new ArrayList<>();
    protected boolean endOfGame;
    protected int nbBienPlace, nbDeCoups;

    public Joueurs(Configuration config) {
        this.config = config;
    }

    /**
     * Determine une combinaison (Aleatoire pour JoueurOrdi, Entree au clavier pour JoueurHumain)
     *
     * @param tabCombi : combinaison dans un tableau de chiffre
     * @return tableau de chiffres : combinaison du joueur
     */
    public abstract ArrayList<Integer> combi(ArrayList<Integer> tabCombi);

    /**
     * Determine les conditions (victoire ou défaite) pour sortir du jeu
     *
     * @param nbBienPlace : nombre de chiffres correspondants au code secret
     * @param tabCombi : combinaison prise en compte pour évaluer les conditions de sortie : tabCombiRandom pour l'ordinateur, tabCombiInput pour le joueur
     * @return boolean : true si les conditions de sortie de jeu sont respectees (vistoire ou défaite), false dans le cas contraire
     */
    public abstract boolean conditionsDeSortie(int nbBienPlace, ArrayList<Integer> tabCombi);

    /**
     * Affiche la combinaison secrete de l'ordinateur quand le mode Developpeur est active
     *
     * @param tabCombiRandom : combinaison de l'ordinateur à afficher en mode developpeur
     */
    public void modeDev(ArrayList<Integer> tabCombiRandom) {
        if (config.getModeDev()) {
            System.out.println("Le code secret de l'ordinateur est : " + intToString(tabCombiRandom));
        }
    }

    /**
     * Compare 2 tableaux (proposition et code secret) et donne les indices (+/-/=)
     *
     * @param tabAttaquant : proposition
     * @param tabDefenseur : code secret
     * @return : tableau d'indices
     */
    public ArrayList<Character> indicesResolution(ArrayList<Integer> tabAttaquant, ArrayList<Integer> tabDefenseur) {
        char indice;
        tabIndice.clear();
        for (int i = 0; i < config.getNbCases(); i++) {
            if (tabAttaquant.get(i) > tabDefenseur.get(i)) {
                indice = '-';
                tabIndice.add(indice);
            } else if (tabAttaquant.get(i) < tabDefenseur.get(i)) {
                indice = '+';
                tabIndice.add(indice);
            } else if (tabAttaquant.get(i).equals(tabDefenseur.get(i))) {
                indice = '=';
                tabIndice.add(indice);
            }
        }
        return tabIndice;
    }

    /**
     * Initialise la variable nbDeCoups à 0
     */
    public void initNbDeCoups() {
        nbDeCoups = 0;
    }

    /**
     * Recupere le nombre de chiffres bien places apres analyse des indices
     *
     * @param tabIndice : tableau des indices (+,- ou =)
     * @param  nbBienPlace : nombre de chiffres de la proposition correspondants au code secret
     * @return : nombre de chiffres bien placés
     */
    public int getNbBienPlace(ArrayList<Character> tabIndice, int nbBienPlace) {
        this.tabIndice = tabIndice;
        this.nbBienPlace = nbBienPlace;
        nbBienPlace = 0;
        for (int i = 0; i < tabIndice.size(); i++) {
            if (tabIndice.get(i) == '=') nbBienPlace++;
        }
        return nbBienPlace;
    }

    /**
     * Transforme un ArrayList de chiffre en String
     *
     * @param tableau : ArrayList de chiffre
     * @return : string
     */
    public String intToString(ArrayList<Integer> tableau) {
        ArrayList<String> intToString = new ArrayList<>();
        for (Integer n : tableau) {
            intToString.add(String.valueOf(n));
        }
        String str = String.join("", intToString);
        return str;
    }

    /**
     * Enregistre les propositions dans un tableau
     *
     * @param tabCombi : tableau de combinaison  : tabCombiRandom ou tabCombiInput selon le joueur
     * @return tableau des combinaisons jouées
     */
    public ArrayList<String> combinaisonsDejaJouees(ArrayList<Integer> tabCombi) {
        tabCombiDejaJouees.add(intToString(tabCombi));
        return tabCombiDejaJouees;
    }

    /**
     * Enregistre les indices correspondants aux propositions faites dans un tableau
     *
     * @param tabIndice : tableau des indices
     * @return tabbleau d'indices
     */
    public ArrayList<String> indicesDejaJoues(ArrayList<Character> tabIndice) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : tabIndice) {
            sb.append(ch);
        }
        String strIndices = sb.toString();
        tabIndicesDejaJoues.add(strIndices);
        return tabIndicesDejaJoues;
    }

    /**
     * Affiche le nombre de tentative, la proposition et les indices correspondants
     *
     * @param tabCombi : tableau de combinaison  : tabCombiRandom ou tabCombiInput selon le joueur
     * @param tabIndice : tableau des indices
     */
    public void displayResult(ArrayList<Integer> tabCombi, ArrayList<Character> tabIndice) {
        combinaisonsDejaJouees(tabCombi);
        indicesDejaJoues(tabIndice);
        for (int i = 0; i < nbDeCoups; i++) {
            System.out.println("#" + (i + 1) + " Proposition : " + tabCombiDejaJouees.get(i) + " -> Réponse : " + tabIndicesDejaJoues.get(i));
        }
    }
}
