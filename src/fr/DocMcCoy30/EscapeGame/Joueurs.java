package fr.DocMcCoy30.EscapeGame;

import java.util.ArrayList;

public abstract class Joueurs {

    protected Configuration config;
    protected ArrayList<Character> tabIndice = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput = new ArrayList<>();
    protected ArrayList<String> tabCombiDejaJouees = new ArrayList<>();
    protected boolean endOfGame;
    protected int nbBienPlace, nbBienPlace1, nbBienPlace2, nbDeCoups;

    public Joueurs(Configuration config) {
        this.config = config;
    }
    
    /**
     * Détermination d'une combinaison (définie dans classes filles (Random pour fr.DocMcCoy30.EscapeGame.JoueurOrdi, Scanner pour fr.DocMcCoy30.EscapeGame.JoueurHumain)
     * @param tabCombi : combinaison dans un tableau de chiffre
     * @return
     */
    public abstract ArrayList<Integer> combi(ArrayList<Integer> tabCombi);

    public void modeDev(ArrayList <Integer> tabCombiRandom){
        if (config.getModeDev()) System.out.println("Le code secret est : " + intToString(tabCombiRandom));
    }

    /**
     * compare 2 tableaux (proposition et code secret) et donne les indices (+/-/=)
     * @param tabAttaquant : proposition
     * @param tabDefenseur : code secret
     * @return : tableau d'indices
     */
    public ArrayList<Character> indicesResolutionPM(ArrayList<Integer> tabAttaquant, ArrayList<Integer> tabDefenseur) {
        char indice;
        tabIndice.clear();
        for (int i = 0; i < config.getNbCases(); i++) {
            if (tabAttaquant.get(i) > tabDefenseur.get(i)) {
                indice = '-';
                tabIndice.add(indice);
                System.out.print(indice);
            } else if (tabAttaquant.get(i) < tabDefenseur.get(i)) {
                indice = '+';
                tabIndice.add(indice);
                System.out.print(indice);
            } else if (tabAttaquant.get(i).equals(tabDefenseur.get(i))) {
                indice = '=';
                tabIndice.add(indice);
                System.out.print(indice);
            }
        }
        System.out.println();
        return tabIndice;
    }

    /**
     * Détermine les conditions pour sortir du jeu en mode challenger ou défenseur
     * @return : fin du jeu (true/false) selon que les conditions sont remplies ou non
     */
    public boolean conditionsDeSortie() {
        endOfGame = false;
        if ((nbBienPlace != config.getNbCases()) && (nbDeCoups < config.getNbEssais())) {
            nbDeCoups++;
            System.out.println("Essai n°" + nbDeCoups);
        } else if ((nbBienPlace == config.getNbCases()) && (nbDeCoups <= config.getNbEssais())) {
            System.out.println("Victoire en " + nbDeCoups + " coup !");
            endOfGame = true;
        } else if ((nbBienPlace != config.getNbCases()) && (nbDeCoups >= config.getNbEssais())) {
            System.out.println("Nombre maximum d'essais atteints.");
            System.out.println();
            endOfGame = true;
        }
        return endOfGame;
    }

    /**
     * determine les conditions pour sortir du jeu en mode duel
     * @param nbBienPlace1 : nombre de chiffres trouvés par Joueur Humain
     * @param nbBienPlace2 : nombre de chiffres trouvés par PC
     * @return : fin du jeu (true/false) selon que les conditions sont remplies ou non
     */
    public boolean conditionsDeSortieDuel(int nbBienPlace1, int nbBienPlace2) {
        this.nbBienPlace1 = nbBienPlace1;
        this.nbBienPlace2 = nbBienPlace2;
        endOfGame = false;
        if (((nbBienPlace1 != config.getNbCases()) && (nbDeCoups < config.getNbEssais())) &&
                ((nbBienPlace2 != config.getNbCases()) && (nbDeCoups < config.getNbEssais()))) {
            nbDeCoups++;
            System.out.println("Essai n°" + nbDeCoups);
        } else if ((nbBienPlace1 == config.getNbCases()) && (nbBienPlace2 != config.getNbCases()) && (nbDeCoups <= config.getNbEssais())) {
            System.out.println("Victoire en " + nbDeCoups + " coup !");
            endOfGame = true;
        } else if ((nbBienPlace2 == config.getNbCases()) && (nbBienPlace1 != config.getNbCases()) && (nbDeCoups <= config.getNbEssais())) {
            System.out.println("Victoire en " + nbDeCoups + " coup !");
            endOfGame = true;
        } else if ((nbBienPlace1 == config.getNbCases()) && (nbBienPlace2 == config.getNbCases()) && (nbDeCoups <= config.getNbEssais())) {
            System.out.println("Match Nul !");
            System.out.println();
            endOfGame = true;
        } else if ((nbBienPlace1 != config.getNbCases()) && (nbBienPlace2 != config.getNbCases()) && (nbDeCoups >= config.getNbEssais())) {
            System.out.println("Nombre maximum d'essais atteints.");
            System.out.println();
            endOfGame = true;
        }
        return endOfGame;
    }

    public void initNb() {
        nbBienPlace = 0;
        nbDeCoups = 0;
    }

    /**
     * récupère le nombre de chiffres bien placés après analyse des indices
     * @return : nombre de chiffres bien placés
     */
    public int getNbBienPlace() {
        nbBienPlace = 0;
        for (int i = 0; i < tabIndice.size(); i++) {
            if (tabIndice.get(i) == '=') nbBienPlace++;
        }
        return nbBienPlace;
    }

    /**
     * tranforme un tableau de chiffre en String
     * @param tableau
     * @return
     */
    public String intToString(ArrayList<Integer> tableau) {
        ArrayList<String> intToString = new ArrayList<>();
        for (Integer n : tableau) {
            intToString.add(String.valueOf(n));
        }
        String str = String.join("", intToString);
        return str;
    }

    public ArrayList<String> combinaisonsDejaJouees() {
        tabCombiDejaJouees.add(intToString(tabCombiRandom));
        return tabCombiDejaJouees;
    }

}
