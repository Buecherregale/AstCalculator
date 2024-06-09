package parse.impl.token;

import parse.api.token.Token;

public enum BinaryOperationToken implements Token<String> {
    ADD("+", 1, Double::sum),
    SUBTRACT("-", 1, (a, b) -> a - b),
    MULTIPLY("*", 2, (a, b) -> a * b),
    DIVIDE("/", 2, (a, b) -> a / b),
    POWER("^", 3, Math::pow);

    private final String value;
    private final int priority;
    private final Operations.BinaryOperation<Double, Double> operation;

    BinaryOperationToken(String value, int priority, Operations.BinaryOperation<Double, Double> operation) {
        this.value = value;
        this.priority = priority;
        this.operation = operation;
    }

    @Override
    public String value() {
        return value;
    }

    public int priority() {
        return priority;
    }

    public Operations.BinaryOperation<Double, Double> operation() {
        return operation;
    }
}
