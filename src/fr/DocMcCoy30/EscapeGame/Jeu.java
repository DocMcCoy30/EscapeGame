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

    /**
     * Initialise les variables nbBienPlaces
     */
    public void initNbDejaJoue() {
        nbBienPlace = 0;
        nbBienPlace1 = 0;
        nbBienPlace2 = 0;
    }

    /**
     * Jeu en mode challenger
     */
    public void challenger() {
        initNbDejaJoue();
        pc.initNbDeCoups();
        pc.tabCombiDejaJouees.clear();
        pc.tabIndicesDejaJoues.clear();
        pc.combi(tabCombiRandom);
        pc.modeDev(tabCombiRandom);
        while (!pc.conditionsDeSortie(nbBienPlace)) {
            jh.combi(tabCombiInput);
            tabIndice = pc.indicesResolution(tabCombiInput, tabCombiRandom);
            pc.displayResult(tabCombiInput, tabIndice);
            nbBienPlace = pc.getNbBienPlace(tabIndice, nbBienPlace);
        }
    }

    /**
     * Jeu en mode défenseur
     */
    public void defenseur() {
        initNbDejaJoue();
        jh.initNbDeCoups();
        jh.tabCombiDejaJouees.clear();
        jh.tabIndicesDejaJoues.clear();

        jh.propositionInitiale();
        jh.combi(tabCombiInput);
        pc.initTabPc(tabCombiRandom);
        pc.tabBornesMinMax(tabBornes);
        while (!jh.conditionsDeSortie(nbBienPlace)) {
            tabIndice = jh.indicesResolution(tabCombiRandom, tabCombiInput);
            jh.displayResult(tabCombiRandom, tabIndice);
            jh.validationIndices();
            nbBienPlace = jh.getNbBienPlace(tabIndice, nbBienPlace);
            pc.proposition(tabCombiRandom, tabIndice, tabBornes);
        }
    }

    /**
     * Jeu en mode duel
     */
    public void duel() {
        initNbDejaJoue();
        pc.initNbDeCoups();
        pc.tabCombiDejaJouees.clear();
        pc.tabIndicesDejaJoues.clear();
        pc.combi(tabCombiRandom);

        jh.initNbDeCoups();
        jh.tabCombiDejaJouees.clear();
        jh.tabIndicesDejaJoues.clear();
        jh.propositionInitiale();
        jh.combi(tabCombiInput2);
        pc.initTabPc(tabCombiRandom2);
        pc.tabBornesMinMax(tabBornes);

        pc.modeDev(tabCombiRandom);

        while ((!jh.conditionsDeSortie(nbBienPlace1))&&(!pc.conditionsDeSortie(nbBienPlace2)))  {

            System.out.println("Challenger, votre proposition :");
            jh.combi(tabCombiInput);
            tabIndice = pc.indicesResolution(tabCombiInput, tabCombiRandom);
            System.out.println("nombre de coup = "+pc.nbDeCoups);
            pc.displayResult(tabCombiInput, tabIndice);
            nbBienPlace1 = pc.getNbBienPlace(tabIndice, nbBienPlace);

            System.out.println("Defenseur");
            tabIndice2 = jh.indicesResolution(tabCombiRandom2, tabCombiInput2);
            System.out.println("nombre de coup = "+jh.nbDeCoups);
            jh.displayResult(tabCombiRandom2, tabIndice2);
            jh.validationIndices();
            nbBienPlace2 = jh.getNbBienPlace(tabIndice2, nbBienPlace);
            pc.proposition(tabCombiRandom2, tabIndice2, tabBornes);
        }

    }
}

