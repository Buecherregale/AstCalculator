package parse.impl.node;

import parse.api.node.Ast;
import parse.api.node.AstParser;
import parse.api.node.Node;
import parse.api.token.*;
import parse.api.util.Pair;
import parse.impl.token.*;

public class StringAstParserImpl implements AstParser<String> {

    @Override
    public Ast<String> parse(Token<String>[] tokens) {

        Node<String> root = new NodeImpl(new RootToken());

        Node<String> last = null;
        Node<String> parent = root;

        for(Token<String> token : tokens) {
            var res = handleToken(token, parent, last);
            parent = res.u();
            last = res.t();
        }

        return new AstImpl(root);
    }

    /**
     *
     * @param token token to handle
     * @param parent parent node
     * @param last  last placed node
     * @return pair of placed node and new parent
     */
    private Pair<Node<String>, Node<String>> handleToken(Token<String> token, Node<String> parent, Node<String> last) {
        Node<String> node = new NodeImpl(token);

        // hard code step up for uot and bot
        while((parent.children().length > 0 && parent.token() instanceof UnaryOperationToken uot && uot.isPrefix())
                || (parent.children().length > 1 && parent.token() instanceof BinaryOperationToken))
            parent = parent.parent();

        final var nextParent = switch (token) {
                case ValueToken ignored:
                    parent.addChild(node);
                    node.setParent(parent);
                    yield parent;
                case DepthIncreasingToken ignored:
                    parent.addChild(node);
                    node.setParent(parent);
                    yield node;
                case DepthDecreasingToken ignored:
                    node = last;
                    yield parent.parent();
                case UnaryOperationToken uot:
                    if(uot.isPrefix()) {
                        parent.addChild(node);
                        node.setParent(parent);
                        yield node;
                    } else {
                        node.setParent(parent.parent());

                        parent.parent().addChild(node);
                        parent.parent().removeChild(parent);
                        parent.setParent(node);

                        node.addChild(parent);
                        yield parent.parent();
                    }
                case BinaryOperationToken bot:
                    placeCandidateBinaryLeftChild(node, last, bot);
                    yield node;
                default:
                    throw new UnsupportedOperationException("Token type not supported: " + token.getClass().getSimpleName());
        };
        return new Pair<>(node, nextParent);
    }

    private void placeCandidateBinaryLeftChild(Node<String> thisNode, Node<String> last, BinaryOperationToken thisBot) {
        Node<String> before = last;
        Node<String> curr = last.parent();

        while(!(curr.token() instanceof RootToken) && (
                !(curr.token() instanceof UnaryOperationToken)
                || !(curr.token() instanceof BinaryOperationToken)
                || !(curr.token() instanceof DepthIncreasingToken)
        )) {
            before = curr;
            curr = curr.parent();
        }

        if(before.token() instanceof BinaryOperationToken bot) {
            if(bot.priority() < thisBot.priority()) {    // priority & left to right

                // higher priority has to be child of lower priority
                // before is bot with lower priority
                var rightChild = before.children()[1];  // the process fails here, if two binary operations are next to each other as in 1++2 because before does not have a right child yet
                before.removeChild(rightChild);

                thisNode.addChild(rightChild);
                rightChild.setParent(thisNode);

                before.addChild(thisNode);
                thisNode.setParent(before);
                return;
            }
        }
        // this node becomes parent of before
        if(before.parent() != null) {
            before.parent().addChild(thisNode);
            before.parent().removeChild(before);
        }

        thisNode.setParent(before.parent());
        thisNode.addChild(before);
        before.setParent(thisNode);
    }
}
