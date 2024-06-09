package parse.api.node;

import parse.api.token.Token;

public interface AstParser<I> {
    Ast<I> parse(Token<I>[] tokens);
}
