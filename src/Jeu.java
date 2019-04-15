import java.util.ArrayList;

public class Jeu {

    JoueurHumain jh = new JoueurHumain();
    JoueurOrdi pc = new JoueurOrdi();

    protected ArrayList<Character> tabIndice = new ArrayList<>();
    protected ArrayList<Character> tabIndice2 = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom2 = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput2 = new ArrayList<>();
    protected ArrayList<Integer> propoPC = new ArrayList<>();
    protected int nbBienPlace, nbBienPlace1, nbBienPlace2, nbMalPlace, nbDeCoups;

    public void challenger() {
        pc.initNb();
        pc.combi(tabCombiRandom);
        pc.modeDev(tabCombiRandom);
        while (!pc.conditionsDeSortie()) {
            jh.combi(tabCombiInput);
            pc.indicesResolutionPM(tabCombiInput, tabCombiRandom);
            pc.getNbBienPlace();
        }
    }

    public void defenseur() {
        jh.initNb();
        jh.combi(tabCombiInput);
        pc.combi(tabCombiRandom);
        while (!jh.conditionsDeSortie()) {
            jh.validationIndices(tabCombiInput, tabCombiRandom);
            tabIndice = jh.indicesResolutionPM(tabCombiRandom, tabCombiInput);
            jh.validationIndices2();
            nbBienPlace = jh.getNbBienPlace();
            pc.proposition(tabCombiRandom, tabIndice);
        }
    }

    public void duel() {
        nbBienPlace1 = 0;
        nbBienPlace2 = 0;
        pc.initNb();
        pc.combi(tabCombiRandom);
        jh.combi(tabCombiInput2);
        pc.combi(tabCombiRandom2);
        System.out.println("J'entre ma combinaison");
        pc.modeDev(tabCombiRandom);
        while (!jh.conditionsDeSortieDuel(nbBienPlace1, nbBienPlace2)) {
            System.out.println("A vous de trouver ma combinaison");
            jh.combi(tabCombiInput);
            pc.indicesResolutionPM(tabCombiInput, tabCombiRandom);
            nbBienPlace1 = pc.getNbBienPlace();
            System.out.println("A moi de trouver votre combinaison");
            jh.validationIndices(tabCombiInput2, tabCombiRandom2);
            tabIndice2 = jh.indicesResolutionPM(tabCombiRandom2, tabCombiInput2);
            jh.validationIndices2();
            nbBienPlace2 = jh.getNbBienPlace();
            pc.proposition(tabCombiRandom2, tabIndice2);
        }
    }
}

