package ceHash;

import edu.princeton.cs.algs4.LinearProbingHashST;

public class Predictor {

    private String context = "****";
    private LinearProbingHashST<String, MoveCounter> contextMap = new LinearProbingHashST<>();

    public Predictor() {
        contextMap.put(context, new MoveCounter());
    }

    public Move predict() {

    }

    public void recordMove(Move m) {
        StringBuilder stringBuilder = new StringBuilder();
        if(m == null) throw new NullPointerException();
        stringBuilder.append(context.charAt(1));
        stringBuilder.append(context.charAt(2));
        stringBuilder.append(context.charAt(3));
        stringBuilder.append(m.asChar());
        context = stringBuilder.toString();
        contextMap.put(context, new MoveCounter());
    }

}
