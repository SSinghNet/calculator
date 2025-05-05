import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer {

    public static ArrayList<Token> parseExpressionString(String expressionString) throws Exception {
        ArrayList<String> words = new ArrayList<String>(
                Arrays.asList(expressionString.split("(?<=[-+*/^])|(?=[-+*/^])")));
        words.removeIf(n -> n.equals(" "));
        for (int i = 0; i < words.size(); i++) {
            words.set(i, words.get(i).trim());
        }

        System.out.println(words);

        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).matches("[\\+\\-\\*\\/\\^]")) {
                indexes.add(i);
            }
        }

        while (indexes.size() > 1) {
            int i = indexes.size() - 1;
            int j = indexes.get(i);
            if (j - 1 == indexes.get(i - 1)) {
                words.set(j + 1, words.get(j) + words.get(j + 1));
                words.remove(j);
                indexes.remove(i);
                indexes.remove(i - 1);
            } else {
                indexes.remove(i);
            }
        }
        if (words.get(0).matches("[\\+\\-\\*\\/]")) {
            words.set(1, words.get(0) + words.get(1));
            words.remove(0);
        }

        System.out.println(words);
        ArrayList<Token> tokens = new ArrayList<Token>();
        for (String word : words) {
            try {
                tokens.add(parseWord(word));
            } catch (Exception e) {
                throw new Exception("Invalid Expression.");
            }
        }

        return tokens;
    }

    private static Token parseWord(String word) throws Exception {
        switch (word) {
            case "+":
                return new Token(TokenType.ADD);
            case "-":
                return new Token(TokenType.SUBTRACT);
            case "*":
                return new Token(TokenType.MULTIPLY);
            case "/":
                return new Token(TokenType.DIVIDE);
            case "^":
                return new Token(TokenType.EXPONENT);
        }

        try {
            double number = Float.parseFloat(word);
            return new Token(TokenType.NUMBER, number);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid Word");
        }
    }

}
