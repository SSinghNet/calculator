public class Expression {
    private Expression leftExpression;
    private Expression rightExpression;
    private TokenType operation;

    private Token leftToken;
    private Token rightToken;

    public Expression(Expression leftExpression, Expression rightExpression, TokenType operation) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operation = operation;
    }

    public Expression(Token leftToken, Token rightToken, TokenType operation) {
        this.leftToken = leftToken;
        this.rightToken = rightToken;
        this.operation = operation;
    }
}
