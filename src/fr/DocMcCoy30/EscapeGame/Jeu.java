package fr.DocMcCoy30.EscapeGame;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

    Configuration config;
    JoueurHumain jh;
    JoueurOrdi pc;

    protected ArrayList<Character> tabIndice = new ArrayList<>();
    protected ArrayList<Character> tabIndice2 = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput = new ArrayList<>();
    protected ArrayList<Integer> tabCombiRandom2 = new ArrayList<>();
    protected ArrayList<Integer> tabCombiInput2 = new ArrayList<>();
    protected List<Bornes> tabBornes = new ArrayList<>();
    protected int nbBienPlace, nbBienPlace1, nbBienPlace2;


    public Jeu(Configuration config) {
        this.config = config;
        jh = new JoueurHumain(config);
        pc = new JoueurOrdi(config);
    }

    public void challenger() {
        pc.initNb();
        pc.tabindicesDejaJoues.clear();
        pc.tabCombiDejaJouees.clear();
        pc.combi(tabCombiRandom);
        pc.modeDev(tabCombiRandom);
        while (!pc.conditionsDeSortie()) {
            jh.combi(tabCombiInput);
            tabIndice = pc.indicesResolution(tabCombiInput, tabCombiRandom);
            pc.displayModeChal(tabCombiInput, tabIndice);
            pc.getNbBienPlace();
        }
    }

    public void defenseur() {
        int i = 0;
        tabIndice.clear();
        jh.initNb();
        jh.propositionInitiale();
        jh.combi(tabCombiInput);
        pc.initTabPc(tabCombiRandom);
        pc.tabBornesMinMax(tabBornes);
        while (!jh.conditionsDeSortie()) {
            jh.affichePropositions(tabCombiInput, tabCombiRandom);
            tabIndice = jh.indicesResolution(tabCombiRandom, tabCombiInput);
            System.out.print(pc.indicesDejaJoues(tabIndice).get(i));
            System.out.println();
            jh.validationIndices();
            nbBienPlace = jh.getNbBienPlace();
            pc.proposition(tabCombiRandom, tabIndice, tabBornes);
            i++;
        }
    }

    public void duel() {
        nbBienPlace1 = 0;
        nbBienPlace2 = 0;
        pc.initNb();
        pc.combi(tabCombiRandom);
        jh.combi(tabCombiInput2);
        pc.initTabPc(tabCombiRandom2);
        System.out.println("J'entre ma combinaison");
        pc.modeDev(tabCombiRandom);
        while (!jh.conditionsDeSortieDuel(nbBienPlace1, nbBienPlace2)) {
            System.out.println("A vous de trouver ma combinaison");
            jh.combi(tabCombiInput);
            pc.indicesResolution(tabCombiInput, tabCombiRandom);
            nbBienPlace1 = pc.getNbBienPlace();
            System.out.println("A moi de trouver votre combinaison");
            jh.affichePropositions(tabCombiInput2, tabCombiRandom2);
            tabIndice2 = jh.indicesResolution(tabCombiRandom2, tabCombiInput2);
            jh.validationIndices();
            nbBienPlace2 = jh.getNbBienPlace();
            pc.proposition(tabCombiRandom2, tabIndice2, tabBornes);
        }
    }
}

