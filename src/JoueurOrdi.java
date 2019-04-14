import java.util.ArrayList;
import java.util.Scanner;

public class JoueurOrdi extends Joueurs {

    Scanner sc = new Scanner(System.in);

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

    public int digitRandom(int lower, int higher) {
        int random = (int) (Math.random() * (higher - lower)) + lower;
        return random;
    }


    public ArrayList<Integer> proposition(ArrayList<Integer> tabCombiRandom, ArrayList<Character> tabIndice) {
        this.tabCombiRandom = tabCombiRandom;
        this.tabIndice = tabIndice;
        for (int i = 0; i < tabIndice.size(); i++) {
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

    public void modeDev(){
        if (config.getModeDev()==true){
            System.out.println("Le code secret est : "+intToString(tabCombiRandom));
        }
    }
}






