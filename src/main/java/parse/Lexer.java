package parse;

import java.util.*;

public class Lexer {
    private final static Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();

        addKeyword(TokenType.KW_PLAY, Aliases.get_aliases_for(TokenType.KW_PLAY));
        addKeyword(TokenType.KW_STOP, Aliases.get_aliases_for(TokenType.KW_STOP));
        addKeyword(TokenType.KW_PAUSE, Aliases.get_aliases_for(TokenType.KW_PAUSE));
        addKeyword(TokenType.KW_QUEUE, Aliases.get_aliases_for(TokenType.KW_QUEUE));
        addKeyword(TokenType.KW_SKIP, Aliases.get_aliases_for(TokenType.KW_SKIP));
        addKeyword(TokenType.KW_RESUME, Aliases.get_aliases_for(TokenType.KW_RESUME));
        addKeyword(TokenType.KW_CERBERUS, Aliases.get_aliases_for(TokenType.KW_CERBERUS));
        addKeyword(TokenType.KW_CACHE, Aliases.get_aliases_for(TokenType.KW_CACHE));
        addKeyword(TokenType.KW_LIST_QUEUE, Aliases.get_aliases_for(TokenType.KW_LIST_QUEUE));
        addKeyword(TokenType.KW_CHANGELOG, Aliases.get_aliases_for(TokenType.KW_CHANGELOG));
        addKeyword(TokenType.KW_LEFTORRIGHT, Aliases.get_aliases_for(TokenType.KW_LEFTORRIGHT));
        addKeyword(TokenType.KW_PICKPERSON, Aliases.get_aliases_for(TokenType.KW_PICKPERSON));
        addKeyword(TokenType.KW_FASTFORWARD, Aliases.get_aliases_for(TokenType.KW_FASTFORWARD));
        addKeyword(TokenType.KW_GETPLAYING, Aliases.get_aliases_for(TokenType.KW_GETPLAYING));
        addKeyword(TokenType.KW_REWIND, Aliases.get_aliases_for(TokenType.KW_REWIND));
    }

    int nextCh;
    char[] char_buffer;
    int current_column;
    int buffer_length;
    int buffer_pos;
    String input_stream;
    ArrayList<Token> tokens;

    public Lexer(String input_stream) {
        this.input_stream = input_stream;
        this.tokens = new ArrayList<Token>();
        this.char_buffer = input_stream.toCharArray();
        this.buffer_length = input_stream.length();

        buffer_pos = 0;
        nextCh = get_next_ch();
        current_column = 0;
    }

    public boolean hasNext() {
        return nextCh != -1;
    }

    public int get_next_ch() {
        if (buffer_pos != buffer_length) {
            current_column++;
            return char_buffer[buffer_pos++];
        }

        return -1;
    }

    public Token get_next_token() {

        char ch;

        do {
            ch = (char) nextCh;
            nextCh = get_next_ch();

            // Command start
            if (ch == '-')
                return new Token(TokenType.START_SYMBOL, current_column, null);


            if (!Character.isWhitespace(ch))
                return get_identifier_token(ch, current_column);

            switch (ch) {
                case ' ': // skip whitespace
                    break;
            }

        } while(true);


    }

    public ArrayList<Token> get_stream() {
        while (hasNext())
            tokens.add(get_next_token());
        tokens.add(new Token(TokenType.EOL, current_column, null));
        return tokens;
    }

    public Token get_identifier_token(char ch, int position) {
        StringBuilder buf = new StringBuilder();
        buf.append(ch);
        while (nextCh != -1 && !Character.isWhitespace((char) nextCh)) {
            buf.append((char) nextCh);
            nextCh = get_next_ch();
        }
        String word = buf.toString();
        System.out.println("CONSIDERING: " + word);

        if (keywords.containsKey(word))
            return new Token(keywords.get(word), position, null);
        else if (word.matches("[0-9]+"))
            return new Token(TokenType.NUMERIC, position, word);
        else if (word.contains("<@!") && word.contains(">"))
            return new Token(TokenType.USER_MENTION, position, word);
        else
            return new Token(TokenType.ARGUMENT, position, word);

    }

    private static void addKeyword(TokenType keyword, ArrayList<String> aliases) {
        for (String alias : aliases)
            keywords.put(alias, keyword);
    }

}
