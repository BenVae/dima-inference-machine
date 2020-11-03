package com.htwg.dima;

import java.util.ArrayList;

import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.inference.InferenceResult;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.parsing.ast.Constant;
import aima.core.logic.fol.parsing.ast.Predicate;
import aima.core.logic.fol.parsing.ast.Term;
import edu.princeton.cs.introcs.StdOut;

public class App {
    public static void main(String[] args) {
        FOLFCAsk folfcAsk = new FOLFCAsk();

        FOLDomain folDomain = new FOLDomain();

        folDomain.addFunction("American");
        folDomain.addFunction("Missile");

        folDomain.addPredicate("Enemy");
        folDomain.addPredicate("Owns");

        folDomain.addConstant("America");
        folDomain.addConstant("West");
        folDomain.addConstant("Nono");
        folDomain.addConstant("M1");

        FOLKnowledgeBase folKnowledgeBase = new FOLKnowledgeBase(folDomain);

        
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Constant("West"));
        Predicate predicate = new Predicate("Criminal", terms);
        

        InferenceResult ask = folfcAsk.ask(folKnowledgeBase, predicate);
        
        

        StdOut.print(ask.isTrue());
    }
}
