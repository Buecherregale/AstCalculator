package parse.impl.token;

import parse.api.token.Token;

public record ValueToken(String value) implements Token<String> {
}
