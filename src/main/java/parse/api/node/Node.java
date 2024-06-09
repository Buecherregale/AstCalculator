package parse.api.node;

import parse.api.token.Token;

public interface Node<I> {
    Token<I> token();

    /**
     * since the default parser moves left to right through the tokens
     * this implementation has to retain the order of elements so that the sequence of tokens is preserved
     * @return the children of this node (in order)
     */
    Node<I>[] children();
    Node<I> parent();

    void addChild(Node<I> child);
    void removeChild(Node<I> child);
    void setParent(Node<I> parent);
}
