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

        InferenceResult ask = folfcAskPoolClosed();
        
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

    static InferenceResult folfcAskIsAFrog(){
        FOLFCAsk folfcAsk = new FOLFCAsk();

        FOLDomain folDomain = new FOLDomain();
        folDomain.addConstant("Frog");
        folDomain.addConstant("Canary");
        folDomain.addConstant("Green");
        folDomain.addConstant("Yellow");
        folDomain.addConstant("Fritz");

        folDomain.addPredicate("Croaks");
        folDomain.addPredicate("Chirps");
        folDomain.addPredicate("Sings");
        folDomain.addPredicate("EatsFlies");
        folDomain.addPredicate("IsA");
        folDomain.addPredicate("Is");
    

        FOLKnowledgeBase kb = new FOLKnowledgeBase(folDomain);

        kb.tell("((Croaks(x) AND EatsFlies(x)) => IsA(x,Frog))");
        kb.tell("((Chirps(x) AND Sings(x)) => IsA(x,Canary))");

        kb.tell("( IsA(x,Frog) => Is(Green))");
        kb.tell("( IsA(x,Canary) => Is(Yellow))");
        kb.tell("Croaks(Fritz)");
        kb.tell("EatsFlies(Fritz)");

        FOLParser parser = new FOLParser(folDomain);
        Sentence proofQuery = parser.parse("IsA(Fritz, Frog)");

        return folfcAsk.ask(kb, proofQuery);
    }
    
    static InferenceResult folfcAskSunburn(){
        FOLFCAsk folfcAsk = new FOLFCAsk();

        FOLDomain folDomain = new FOLDomain();
        folDomain.addConstant("I");

        folDomain.addPredicate("GoSwimming");
        folDomain.addPredicate("StayInSunTooLong");
        folDomain.addPredicate("GetSunburn");
    

        FOLKnowledgeBase kb = new FOLKnowledgeBase(folDomain);

        kb.tell("( GoSwimming(x) => StayInSunTooLong(x))");
        kb.tell("( StayInSunTooLong(x) => GetSunburn(x))");
        kb.tell("( GoSwimming(I) )");

        FOLParser parser = new FOLParser(folDomain);
        Sentence proofQuery = parser.parse("GetSunburn(I)");

        return folfcAsk.ask(kb, proofQuery);
    }

    static InferenceResult folfcAskPoolClosed(){
        FOLFCAsk folfcAsk = new FOLFCAsk();

        FOLDomain folDomain = new FOLDomain();
        folDomain.addConstant("We");
        folDomain.addConstant("Today");

        folDomain.addPredicate("SunnyThisAfternoon");
        folDomain.addPredicate("WillGoSwimming");
        folDomain.addPredicate("TakeCanoeTrip");
        folDomain.addPredicate("HomeBySunset");
    

        FOLKnowledgeBase kb = new FOLKnowledgeBase(folDomain);

        kb.tell("( NOT SunnyThisAfternoon(Today))");
        kb.tell("( WillGoSwimming(x) => SunnyThisAfternoon(y))");
        kb.tell("( NOT WillGoSwimming(x) => TakeCanoeTrip(x) )");
        kb.tell("( TakeCanoeTrip(x) => HomeBySunset(x) )");

        FOLParser parser = new FOLParser(folDomain);
        Sentence proofQuery = parser.parse("HomeBySunset(We)");

        return folfcAsk.ask(kb, proofQuery);
    }
}

