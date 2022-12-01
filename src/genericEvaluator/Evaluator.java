package genericEvaluator;

public class Evaluator <T extends Comparable<T>>{

    public static <T extends Comparable<T>> boolean evaluateExpression (T left, T right, String operator){
        IEvaluator evaluator = EvaluatorBuilder.build(operator);
        return evaluator.evaluate(left, right);

    }
}
