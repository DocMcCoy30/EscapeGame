import java.util.ArrayList;

public class JoueurOrdi extends Joueurs {

    private int nbRand;

    @Override
    public ArrayList<Integer> combi(ArrayList<Integer> tabCombiRandom) {
        this.tabCombiRandom = tabCombiRandom;
        tabCombiRandom.clear();
        for (int i = 0; i < config.getNbCases(); i++) {
            tabCombiRandom.add(i, digitRandom(0, config.getNbDigits()));
        }
        return tabCombiRandom;
    }

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
            if (tabIndice.get(i) == '-') {
                nbRand = digitRandom(0, tabCombiRandom.get(i));
                tabCombiRandom.set(i, nbRand);
            } else if (tabIndice.get(i) == '+') {
                nbRand = digitRandom((tabCombiRandom.get(i) + 1), (Configuration.getNbDigits() + 1));
                tabCombiRandom.set(i, nbRand);
            } else if (tabIndice.get(i) == '=') {
                nbBienPlace++;
            }
        }
        return tabCombiRandom;
    }


}





