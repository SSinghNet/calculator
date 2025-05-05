import java.util.ArrayList;

public class Tokenizer {
    
    public static ArrayList<Token> parseExpressionString(String expressionString) throws Exception {
        String[] words = expressionString.split("(?<=[-+*/^])|(?=[-+*/^])");
        System.out.println(words);

        ArrayList<Token> tokens = new ArrayList<Token>();
        for (String word : words) {
            try {
                tokens.add(parseWord(word));
            } catch (Exception e) {
                System.out.println(word);
                // throw new Exception("Invalid Expression.");
            }
        }

        return tokens;
    }
    
    private static Token parseWord(String word) throws Exception{
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
