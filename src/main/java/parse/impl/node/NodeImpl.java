package parse.impl.node;

import parse.api.node.Node;
import parse.api.token.Token;

import java.util.ArrayList;
import java.util.List;

public class NodeImpl implements Node<String> {

    private final Token<String> token;
    private final List<Node<String>> children = new ArrayList<>();
    private Node<String> parent;

    public NodeImpl(Token<String> token) {
        this.token = token;
    }

    @Override
    public Token<String> token() {
        return token;
    }

    @Override
    public Node<String>[] children() {
        @SuppressWarnings("unchecked")
        Node<String>[] childrenArray = children.toArray(Node[]::new);
        return childrenArray;
    }

    @Override
    public Node<String> parent() {
        return parent;
    }

    @Override
    public void addChild(Node<String> child) {
        children.add(child);
    }

    @Override
    public void removeChild(Node<String> child) {
        children.remove(child);
    }

    @Override
    public void setParent(Node<String> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return token().value();
    }
}
