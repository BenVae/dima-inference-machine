package com.htwg.dima;

import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.inference.FOLFCAsk;
import aima.core.logic.fol.inference.InferenceResult;
import aima.core.logic.fol.inference.InferenceResultPrinter;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.parsing.FOLParser;
import aima.core.logic.fol.parsing.ast.Sentence;
import edu.princeton.cs.introcs.StdOut;

public class App {
    public static void main(String[] args) {

        InferenceResult ask = folfcAskIsWestCiminal();
        
        StdOut.print(InferenceResultPrinter.printInferenceResult(ask));
    }

    static InferenceResult folfcAskIsWestCiminal(){
        FOLFCAsk folfcAsk = new FOLFCAsk();

        FOLDomain folDomain = new FOLDomain();
        folDomain.addConstant("West");
        folDomain.addConstant("America");
        folDomain.addConstant("M1");
        folDomain.addConstant("Nono");

        folDomain.addPredicate("American");
        folDomain.addPredicate("Weapon");
        folDomain.addPredicate("Sells");
        folDomain.addPredicate("Hostile");
        folDomain.addPredicate("Criminal");
        folDomain.addPredicate("Missile");
        folDomain.addPredicate("Owns");
        folDomain.addPredicate("Enemy");

        FOLKnowledgeBase kb = new FOLKnowledgeBase(folDomain);

        kb.tell("( (((American(x) AND Weapon(y)) AND Sells(x,y,z)) AND Hostile(z)) => Criminal(x))");
        kb.tell("Owns(Nono, M1)");
        kb.tell("Missile(M1)");
        kb.tell("((Missile(x) AND Owns(Nono,x)) => Sells(West,x,Nono))");
        kb.tell("(Missile(x) => Weapon(x))");
        kb.tell("(Enemy(x,America) => Hostile(x))");
        kb.tell("American(West)");
        kb.tell("Enemy(Nono,America)");

        FOLParser parser = new FOLParser(folDomain);
        Sentence proofQuery = parser.parse("Criminal(West)");

        return folfcAsk.ask(kb, proofQuery);
    }
}
