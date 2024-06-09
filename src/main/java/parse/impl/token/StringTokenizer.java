package parse.impl.token;

import parse.api.token.Token;
import parse.api.token.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class StringTokenizer implements Tokenizer<String> {

    private static Token<String> unsafeOf(String value) {
        for(var t: BinaryOperationToken.values()) {
            if(t.value().equals(value)) {
                return t;
            }
        }
        for(var t: UnaryOperationToken.values()) {
            if(t.value().equals(value)) {
                return t;
            }
        }
        for(var t: DepthDecreasingToken.values()) {
            if(t.value().equals(value)) {
                return t;
            }
        }
        for(var t: DepthIncreasingToken.values()) {
            if(t.value().equals(value)) {
                return t;
            }
        }

        return null;
    }

    @Override
    public Token<String>[] tokenize(String in) {
        final List<Token<String>> tokens = new ArrayList<>();

        int i = 0;

        StringBuilder buffer = new StringBuilder();

        while(i < in.length()) {
            while(i < in.length() && (Character.isDigit(in.charAt(i)) || in.charAt(i) == '.')) {
                buffer.append(in.charAt(i));
                i++;
            }
            if(!buffer.isEmpty()) {
                tokens.add(new ValueToken(buffer.toString()));
                buffer = new StringBuilder();
            }
            while(i < in.length() && unsafeOf(buffer.toString()) == null) {
                buffer.append(in.charAt(i));
                i++;
            }
            if(!buffer.isEmpty()) {
                tokens.add(unsafeOf(buffer.toString()));
                buffer = new StringBuilder();
            }
        }

        @SuppressWarnings("unchecked")
        Token<String>[] tokensArray = tokens.toArray(Token[]::new);
        return tokensArray;
    }
}
