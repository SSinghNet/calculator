import java.util.ArrayList;

public class Calculator {
    public static double calculate(String expressionString) throws Exception {
        // parse tokens
        try {
            ArrayList<Token> tokens = Tokenizer.parseExpressionString(expressionString);
            System.out.println(tokens);

            System.out.println(calculateExpression(tokens));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Calculation failed.");
        }
        return 0;
    }

    private static double calculateExpression(ArrayList<Token> tokens) {
        int index = -1;

        if (tokens.size() == 1) {
            return tokens.get(0).value();
        }

        double value = 0;
        if ((index = tokens.indexOf(new Token(TokenType.EXPONENT))) != -1) {
            value = exponent(tokens.get(index - 1).value(), tokens.get(index + 1).value());
        } else if (tokens.indexOf(new Token(TokenType.MULTIPLY)) != -1
                || tokens.indexOf(new Token(TokenType.DIVIDE)) != -1) {
            if (tokens.indexOf(new Token(TokenType.MULTIPLY)) != -1
                    && (tokens.indexOf(new Token(TokenType.MULTIPLY)) < tokens.indexOf(new Token(TokenType.DIVIDE)) || tokens.indexOf(new Token(TokenType.DIVIDE)) == -1)) {
                index = tokens.indexOf(new Token(TokenType.MULTIPLY));
                value = multiply(tokens.get(index - 1).value(), tokens.get(index + 1).value());
            } else {
                index = tokens.indexOf(new Token(TokenType.DIVIDE));
                value = divide(tokens.get(index - 1).value(), tokens.get(index + 1).value());
            }
        } else if (tokens.indexOf(new Token(TokenType.ADD)) != -1
                || tokens.indexOf(new Token(TokenType.SUBTRACT)) != -1) {
            if (tokens.indexOf(new Token(TokenType.ADD)) != -1
                    && (tokens.indexOf(new Token(TokenType.ADD)) < tokens.indexOf(new Token(TokenType.SUBTRACT)) || tokens.indexOf(new Token(TokenType.SUBTRACT)) == -1) ) {
                index = tokens.indexOf(new Token(TokenType.ADD));
                value = add(tokens.get(index - 1).value(), tokens.get(index + 1).value());
            } else {
                index = tokens.indexOf(new Token(TokenType.SUBTRACT));
                value = subtract(tokens.get(index - 1).value(), tokens.get(index + 1).value());
            }
        }

        tokens.remove(index + 1);
        tokens.remove(index);

        try {
            tokens.set(index - 1, new Token(TokenType.NUMBER, value));
        } catch (Exception e) {
            System.out.println("Invalid thing");
            return 0;
        }

        System.out.println(tokens);
        return calculateExpression(tokens);
    }

    private static double exponent(double a, double b) {
        return Math.pow(a, b);
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) {
        return a / b;
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

}
