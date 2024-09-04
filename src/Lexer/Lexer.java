package Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static final String IDENTIFIER_REGEX = "[a-zA-Z_]\\w*\\b";
    private static final String CONSTANT_REGEX = "[0-9]+\\b";
    private static final String INT_KEYWORD_REGEX = "int\\b";
    private static final String VOID_KEYWORD_REGEX = "void\\b";
    private static final String RETURN_KEYWORD_REGEX = "return\\b";
    private static final String OPEN_PAREN_REGEX = "\\(";
    private static final String CLOSE_PAREN_REGEX = "\\)";
    private static final String OPEN_BRACE_REGEX = "\\{";
    private static final String CLOSE_BRACE_REGEX = "\\}";
    private static final String SEMICOLON_REGEX = ";";

    private static final Pattern TOKEN_PATTERNS = Pattern.compile(
            IDENTIFIER_REGEX + "|" +
                    CONSTANT_REGEX + "|" +
                    INT_KEYWORD_REGEX + "|" +
                    VOID_KEYWORD_REGEX + "|" +
                    RETURN_KEYWORD_REGEX + "|" +
                    OPEN_PAREN_REGEX + "|" +
                    CLOSE_PAREN_REGEX + "|" +
                    OPEN_BRACE_REGEX + "|" +
                    CLOSE_BRACE_REGEX + "|" +
                    SEMICOLON_REGEX);

    private String input;
    private int position;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERNS.matcher(input);

        while (matcher.find(position)) {
            String token = matcher.group();

            if (token.matches(INT_KEYWORD_REGEX) || token.matches(VOID_KEYWORD_REGEX)
                    || token.matches(RETURN_KEYWORD_REGEX)) {
                tokens.add(new Token(Token.TokenType.KEYWORD, token));
            } else if (token.matches(IDENTIFIER_REGEX)) {
                tokens.add(new Token(Token.TokenType.IDENTIFIER, token));
            } else if (token.matches(CONSTANT_REGEX)) {
                tokens.add(new Token(Token.TokenType.CONSTANT, token));
            } else if (token.matches(OPEN_PAREN_REGEX)) {
                tokens.add(new Token(Token.TokenType.OPEN_PAREN, token));
            } else if (token.matches(CLOSE_PAREN_REGEX)) {
                tokens.add(new Token(Token.TokenType.CLOSE_PAREN, token));
            } else if (token.matches(OPEN_BRACE_REGEX)) {
                tokens.add(new Token(Token.TokenType.OPEN_BRACE, token));
            } else if (token.matches(CLOSE_BRACE_REGEX)) {
                tokens.add(new Token(Token.TokenType.CLOSE_BRACE, token));
            } else if (token.matches(SEMICOLON_REGEX)) {
                tokens.add(new Token(Token.TokenType.SEMICOLON, token));
            }

            position = matcher.end();
        }

        tokens.add(new Token(Token.TokenType.EOF, "EOF"));
        return tokens;
    }

    public static void main(String[] args) {
        String input = "int\tmain\t(\tvoid)\t{\treturn\t0\t;\t}";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}

class Token {
    public enum TokenType {
        IDENTIFIER, CONSTANT, KEYWORD, OPEN_PAREN, CLOSE_PAREN, OPEN_BRACE, CLOSE_BRACE, SEMICOLON, EOF
    }

    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", value='" + value + '\'' + '}';
    }
}
