package parse.impl.token;

import parse.api.token.Token;

public enum DepthIncreasingToken implements Token<String> {
    BRACKET_OPEN("("),
    FUNCTION_OPEN("{"),
    SQUARE_BRACKET_OPEN("[");

    private final String value;

    DepthIncreasingToken(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
