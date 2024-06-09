package parse.impl.token;

import parse.api.token.Token;

public enum DepthDecreasingToken implements Token<String> {
    BRACKET_CLOSE(")"),
    FUNCTION_CLOSE("}"),
    SQUARE_BRACKET_CLOSE("]");

    private final String value;

    DepthDecreasingToken(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
