package parse.api.token;

public interface Tokenizer<I> {
    Token<I>[] tokenize(I in);
}
