package parse.api.node;

public interface Ast<I> {
    Node<I> root();
    double evaluate();
}
