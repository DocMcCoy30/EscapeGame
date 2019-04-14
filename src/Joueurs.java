import java.util.ArrayList;

public abstract class Joueurs {

    Configuration config = new Configuration();

    protected ArrayList<Character> tabIndice = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput = new ArrayList<>();
    protected ArrayList<String> tabCombiDejaJouees = new ArrayList<>();
    protected boolean dejaJoue[] = new boolean[config.getNbCases()];
    protected boolean endOfGame;
    protected int nbBienPlace, nbBienPlace1, nbBienPlace2, nbMalPlace, nbDeCoups;


    public abstract ArrayList<Integer> combi(ArrayList<Integer> tabCombi);

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
        nbMalPlace = 0;
        nbDeCoups = 0;
    }

    public int getNbBienPlace() {
        nbBienPlace = 0;
        for (int i = 0; i < tabIndice.size(); i++) {
            if (tabIndice.get(i) == '=') nbBienPlace++;
        }
        return nbBienPlace;
    }


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
