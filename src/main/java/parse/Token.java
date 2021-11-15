package parse;

public class Token {
    private final TokenType type;
    private final int position;
    private String argument;

    public Token(TokenType type, int position, String argument) {
        this.type = type;
        this.position = position;
        this.argument = argument;
    }

    public TokenType get_type() {
        return type;
    }

    public int get_position() {
        return position;
    }

    public String get_argument() {
        // If argument is ever null, omit it
        return (argument == null ? "" : argument);
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", position=" + position +
                ", argument='" + argument + '\'' +
                '}';
    }
}
