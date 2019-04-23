package fr.DocMcCoy30.EscapeGame;

import java.util.ArrayList;

public class JoueurOrdi extends Joueurs {

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

    /**
     * Determine un chiffre aléatoire compris entre deux bornes
     * @param lower  : borne basse
     * @param higher : borne haute
     * @return : chiffre aléatoire
     */
    private int digitRandom(int lower, int higher) {
        int random = (int) (Math.random() * (higher - lower)) + lower;
        return random;
    }

    /**
     * effectue une proposition aléatoire après analyse des indices (+/- ou =)
     * @param tabCombiRandom : tableau de chiffres => nouvelle proposition
     * @param tabIndice : tableau d'indices après comparaison entre code secret et proposition
     * @return : nouvelle proposition
     */
    public ArrayList<Integer> proposition(ArrayList<Integer> tabCombiRandom, ArrayList<Character> tabIndice) {
        this.tabCombiRandom = tabCombiRandom;
        this.tabIndice = tabIndice;
        for (int i = 0; i < tabIndice.size(); i++) {
            int nbRand;
            if (tabIndice.get(i) == '-') {
                nbRand = digitRandom(0, tabCombiRandom.get(i));
                tabCombiRandom.set(i, nbRand);
            } else if (tabIndice.get(i) == '+') {
                nbRand = digitRandom((tabCombiRandom.get(i) + 1), (config.getNbDigits() + 1));
                tabCombiRandom.set(i, nbRand);
            } else if (tabIndice.get(i) == '=') {
                nbBienPlace++;
            }
        }
        return tabCombiRandom;
    }


}






