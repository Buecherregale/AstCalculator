package parse.impl.node;

import parse.api.node.Ast;
import parse.api.node.Node;
import parse.impl.token.BinaryOperationToken;
import parse.impl.token.DepthIncreasingToken;
import parse.impl.token.RootToken;
import parse.impl.token.UnaryOperationToken;

public record AstImpl(Node<String> root) implements Ast<String> {

    @Override
    public double evaluate() {
        return evaluateNode(root());
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(root).append("\n");
        nodeToString(root, res);
        return res.toString();
    }

    private void nodeToString(Node<String> node, StringBuilder sb) {
        sb.append("children of ").append(node).append("\n");
        for(var n: node.children())
            sb.append(n).append(" = ").append(evaluateNode(n)).append("\t");
        sb.append("\n");
        for(var n: node.children())
            nodeToString(n, sb);
    }

    private double evaluateNode(Node<String> node) {
        if(node.token() instanceof RootToken)
            return evaluateNode(node.children()[0]);
        if(node.children().length == 0)
            return Double.parseDouble(node.token().value());

        var token = node.token();
        return switch (token) {
            case DepthIncreasingToken ignored -> evaluateNode(node.children()[0]);
            case UnaryOperationToken uot -> uot.operation().evaluate(evaluateNode(node.children()[0]));
            case BinaryOperationToken bot -> {
                if (node.children().length != 2)
                    throw new IllegalStateException("Binary operation token must have exactly 2 children " + token.value() + " has " + node.children().length);
                yield bot.operation().evaluate(evaluateNode(node.children()[0]), evaluateNode(node.children()[1]));
            } default ->
                // depth decreasing token and value token should not exist here
                throw new IllegalStateException("Unknown token type: " + token.getClass().getName());
        };
    }
}
