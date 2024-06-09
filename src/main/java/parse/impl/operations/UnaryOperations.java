package parse.impl.operations;

public final class UnaryOperations {
    private UnaryOperations() {}

    public static double factorial(double n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n % 1 != 0) {
            throw new IllegalArgumentException("Factorial is not defined for non-integer numbers");
        }
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
