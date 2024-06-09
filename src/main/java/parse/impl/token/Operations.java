package parse.impl.token;

public final class Operations {

    public interface BinaryOperation<I, O> {
        O evaluate(I left, I right);
    }

    public interface UnaryOperation<I, O> {
        O evaluate(I operand);
    }

}
