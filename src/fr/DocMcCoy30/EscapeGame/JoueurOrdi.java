package fr.DocMcCoy30.EscapeGame;

import java.util.ArrayList;
import java.util.List;

public class JoueurOrdi extends Joueurs {

    List<Bornes> tabBornes = new ArrayList<>();

    public JoueurOrdi(Configuration config) {
        super(config);
    }

    @Override
    public ArrayList<Integer> combi(ArrayList<Integer> tabCombiRandom) {
        this.tabCombiRandom = tabCombiRandom;
        tabCombiRandom.clear();
        for (int i = 0; i < config.getNbCases(); i++) {
            tabCombiRandom.add(i, digitRandom(0, config.getNbDigits()));
        }
        return tabCombiRandom;
    }

    @Override
    public boolean conditionsDeSortie(int nbBienPlace) {
        this.nbBienPlace = nbBienPlace;
        endOfGame = false;
        if ((nbBienPlace != config.getNbCases()) && (nbDeCoups < config.getNbEssais())) {
            nbDeCoups++;
        } else if ((nbBienPlace == config.getNbCases()) && (nbDeCoups <= config.getNbEssais())) {
            System.out.println("Victoire de l'ordinateur en " + nbDeCoups + " coup !");
            System.out.println();
            endOfGame = true;
        } else if ((nbBienPlace != config.getNbCases()) && (nbDeCoups >= config.getNbEssais())) {
            System.out.println("Nombre maximum d'essais atteints.");
            System.out.println("Perdu : la solution était : "+intToString(tabCombiRandom));
            System.out.println();
            endOfGame = true;
        }
        return endOfGame;
    }

    /**
     * Determine un chiffre aléatoire compris entre deux bornes
     *
     * @param lower  : borne basse
     * @param higher : borne haute
     * @return : chiffre aléatoire
     */
    private int digitRandom(int lower, int higher) {
        int random = (int) (Math.random() * (higher - lower)) + lower;
        return random;
    }

    /**
     * Initialise une List d'objet qui permet d'enregister les bornes (valeurs min et max) pour chaque chiffre de la proposition de l'ordinateur
     *
     * @param tabBornes
     * @return : tableau d'objet Bornes
     */
    public List<Bornes> tabBornesMinMax(List<Bornes> tabBornes) {
        this.tabBornes = tabBornes;
        tabBornes.clear();
        for (int i = 0; i < config.getNbCases(); i++) {
            tabBornes.add(new Bornes(0, 9));
        }
        return tabBornes;
    }

    /**
     * Initialise la premiere proposition de l'ordinateur
     *
     * @param tabCombiRandom
     * @return : tableau de proposition
     */
    public ArrayList<Integer> initTabPc(ArrayList<Integer> tabCombiRandom) {
        this.tabCombiRandom = tabCombiRandom;
        tabCombiRandom.clear();
        for (int i = 0; i < config.getNbCases(); i++) {
            tabCombiRandom.add(5);
        }
        return tabCombiRandom;
    }

    /**
     * Analyse des indices (+/- ou =), redéfinit les bornes min et max,
     * détermine un chiffre aléatoire entre ces deux bornes et effectue une nouvelle proposition
     *
     * @param tabCombiRandom : tableau de chiffres => nouvelle proposition
     * @param tabIndice      : tableau d'indices après comparaison entre code secret et proposition
     * @param tabBornes      : tableau d'objet Bornes
     * @return : nouvelle proposition
     */
    public ArrayList<Integer> proposition(ArrayList<Integer> tabCombiRandom, ArrayList<Character> tabIndice, List<Bornes> tabBornes) {
        this.tabCombiRandom = tabCombiRandom;
        this.tabIndice = tabIndice;
        this.tabBornes = tabBornes;
        for (int i = 0; i < tabIndice.size(); i++) {
            int nbRand;
            if (tabIndice.get(i) == '-') {
                tabBornes.get(i).setBorneMax(tabCombiRandom.get(i));
                nbRand = digitRandom(tabBornes.get(i).getBorneMin(), tabBornes.get(i).getBorneMax());
                tabCombiRandom.set(i, nbRand);
            } else if (tabIndice.get(i) == '+') {
                tabBornes.get(i).setBorneMin(tabCombiRandom.get(i) + 1);
                nbRand = digitRandom(tabBornes.get(i).getBorneMin(), tabBornes.get(i).getBorneMax());
                tabCombiRandom.set(i, nbRand);
            } else if (tabIndice.get(i) == '=') {
                nbBienPlace++;
            }
        }
        return tabCombiRandom;
    }


}






