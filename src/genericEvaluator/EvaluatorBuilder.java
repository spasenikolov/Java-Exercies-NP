package genericEvaluator;

public class EvaluatorBuilder <T extends Comparable<T>>{

    static IEvaluator build (String operator) {
        if(operator.equals(">")) return (a, b) -> a.compareTo(b)>0;
        else if (operator.equals("==")) return (a, b) -> a.compareTo(b)==0;
        else if (operator.equals("!=")) return (a, b) -> a.compareTo(b)!=0;
        else return (a, b) -> a.compareTo(b)<0;
    }
}

