package parse.api.token;

public interface OperationToken<I, O> extends Token<I> {
    int priority();
}
