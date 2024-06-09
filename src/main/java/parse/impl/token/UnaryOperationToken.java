package parse.impl.token;

import parse.api.token.Token;
import parse.impl.operations.UnaryOperations;

public enum UnaryOperationToken implements Token<String> {
    NEGATION("-", true, a -> -a),
    SIN("sin", true, Math::sin),
    COS("cos", true, Math::cos),
    TAN("tan", true, Math::tan),
    ASIN("asin", true, Math::asin),
    ACOS("acos", true, Math::acos),
    ATAN("atan", true, Math::atan),
    SQRT("sqrt", true, Math::sqrt),
    EXP("exp", true, Math::exp),
    LN("ln", true, Math::log),
    LOG("log", true, Math::log10),
    ABS("abs", true, Math::abs),
    CEIL("ceil", true, Math::ceil),
    FLOOR("floor", true, Math::floor),
    ROUND("round", true, Math::rint),
    FAK("!", false, UnaryOperations::factorial)
    ;

    private final String value;
    private final boolean isPrefix;
    private final Operations.UnaryOperation<Double, Double> operation;

    UnaryOperationToken(String value, boolean isPrefix, Operations.UnaryOperation<Double, Double> operation) {
        this.value = value;
        this.isPrefix = isPrefix;
        this.operation = operation;
    }

    @Override
    public String value() {
        return value;
    }

    public boolean isPrefix() {
        return isPrefix;
    }

    public Operations.UnaryOperation<Double, Double> operation() {
        return operation;
    }
}
