public class Token {
    private TokenType tokenType;
    private double value;

    public Token(TokenType tokenType) {
        this.tokenType = tokenType;
        value = 0;
    }

    public Token(TokenType tokenType, double value) throws Exception {
        if (tokenType != TokenType.NUMBER) {
            throw new Exception("Only NUMBER tokens can have a value.");
        }
        this.tokenType = tokenType;
        this.value = value;
    }

    public double value() {
        return value;
    }

    public TokenType type() {
        return tokenType;
    }

    @Override
    public String toString() {
        return tokenType + " " + value;
    }

    @Override
    public boolean equals(Object obj) {
        return tokenType == ( (Token) obj).type() && value == ( (Token) obj).value();
    }
}
