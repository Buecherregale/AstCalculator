package parse;

import parse.api.node.AstParser;
import parse.api.token.Tokenizer;
import parse.impl.node.StringAstParserImpl;
import parse.impl.token.StringTokenizer;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String inp = "1+2*sin(4)*3.242";
        inp = "4+5-6*7+sqrt(9)";
        System.out.println(inp);

        Tokenizer<String> tokenizer = new StringTokenizer();
        var tokens = tokenizer.tokenize(inp);
        System.out.println(Arrays.toString(tokens));

        AstParser<String> parser = new StringAstParserImpl();
        var ast = parser.parse(tokens);
        System.out.println(ast);
        System.out.println(ast.evaluate());
    }
}
