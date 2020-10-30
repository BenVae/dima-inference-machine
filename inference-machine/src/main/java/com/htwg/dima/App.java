package com.htwg.dima;

import java.util.ArrayList;

import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.inference.InferenceResult;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.parsing.ast.Constant;
import aima.core.logic.fol.parsing.ast.Function;
import aima.core.logic.fol.parsing.ast.Predicate;
import aima.core.logic.fol.parsing.ast.Sentence;
import aima.core.logic.fol.parsing.ast.Term;
import aima.core.logic.fol.parsing.ast.Variable;

public class App {
    public static void main(String[] args) {
        FOLFCAsk folbcAsk = new FOLFCAsk();

        FOLDomain folDomain = new FOLDomain();

        folDomain.addFunction("American");
        folDomain.addConstant("West");
        folDomain.addFunction("Missile");
        folDomain.addConstant("M1");
        folDomain.addPredicate("Owns");
        folDomain.addConstant("Nono");
        folDomain.addConstant("M1");
        folDomain.addPredicate("Enemy");
        folDomain.addConstant("Nono");
        folDomain.addConstant("America");

        ArrayList<Term> terms = new ArrayList<>();
        terms.add(new Constant("West"));


        Predicate function = new Predicate("Criminal", terms);

        FOLKnowledgeBase folKnowledgeBase = new FOLKnowledgeBase(folDomain);

        folbcAsk.ask(folKnowledgeBase, function);

        folbcAsk.ask(folKnowledgeBase, function);

    }
}
