package parse;

import Commands.CommandType;

import java.util.ArrayList;

/**
 * class Parser - A recursive descent parser for simbot commands
 * ----- Simbot Grammar -----
 * Command              ->  START_SYMBOL Statement
 * Statement            ->  PlayStatement | ResumeStatement | LeaveStatement | StopStatement | QueueStatement
 *                              | SkipStatement | PermissionStatement | PlaylistStatement | CeberusStatement
 * PlayStatement        ->  "play" | "pla" | "ply" | "pl" | "p"
 * LeaveStatement       ->  "leave" | "disconnect" | "exit" | "dc" | "x" | "shutup" | "fuckoff"
 * StopStatement        ->  "stop" | "halt"
 * PauseStatement       ->  "pause" | "hold" | "fuckup"
 * QueueStatement       ->  "queue" | "q" | "enqueue"
 * SkipStatement        ->  "skip" | "next" | "nxt"
 * ResumeStatement      ->  "resume" | "res"
 * PermissionStatement  ->  "permission" | "perms" | "perm" | "access" Argument ModifyStatement [Argument]
 * PlaylistStatement    ->  "playlist" | "pl" | "playl" | "plist" Argument ModifyStatement [Argument]
 * ModifyStatement      ->  "add" | "remove" | "list"
 * CeberusStatement     ->  "cerberus" | "cerb" | "monitor" UserMention
 * Condition            ->  True | False
 * OnOff                ->  OnAlias | OffAlias
 * OnAlias              ->  "on" | "true" | "1" | "yes" | "positive"
 * OffAlias             ->  "off" | "false" | "0" | "no" | "negative"
 * ArgumentList         ->  Argument {Argument}
 * Argument             ->  ARG
 * StartToken           ->  DASH
 */
public class Parser {

    String input_stream;
    int stream_idx;
    int stream_len;
    ArrayList<Token> stream;
    Lexer lexer;
    boolean parse_success;

    CommandType commandType;
    ArrayList<String> argument_list;

    public Parser(String input_stream) {
        this.input_stream = input_stream;
        this.lexer = new Lexer(input_stream);
        this.stream = lexer.get_stream();
        this.stream_idx = 0;
        this.stream_len = stream.size();
        this.argument_list = new ArrayList<>();
        this.parse_success = true;
        this.commandType = null;
    }

    public void dump_tok_stream() {
        System.out.println("dump");
        for (Token t : stream) {
            System.out.println(t.toString());
        }
    }

    public boolean parse() {
        parse_command();
        return parse_success;
    }

    public CommandType get_parsed_command_type() {
        return commandType;
    }

    public ArrayList<String> get_argument_list() {
        return argument_list;
    }

    private void parse_command() {
        match(TokenType.START_SYMBOL);
        System.out.println("TYPE: " + peek_token().get_type().toString());
        switch(peek_token().get_type()) {
            case KW_PLAY:
                parse_play();
                break;
            case KW_STOP:
                parse_stop();
                break;
            case KW_PAUSE:
                parse_pause();
                break;
            case KW_QUEUE:
                parse_queue();
                break;
            case KW_LIST_QUEUE:
                parse_queue_list();
                break;
            case KW_SKIP:
                parse_skip();
                break;
            case KW_RESUME:
                parse_resume();
                break;
            case KW_CERBERUS:
                parse_cerberus();
                break;
            case KW_CACHE:
                parse_cache();
                break;
            case KW_CHANGELOG:
                parse_changelog();
                break;
            case KW_LEFTORRIGHT:
                parse_leftorright();
                break;
            case KW_PICKPERSON:
                parse_pickperson();
                break;
            default:
                System.out.println("Unknown cmd");
                parse_success = false;
        }
        // Only reached if no valid command specified

    }

    private void parse_play() {
        match(TokenType.KW_PLAY);
        // If we match the play keyword, we can assume that
        // whatever follows is the name/identifier of something to play
        while (!is_match(TokenType.EOL)) {
            System.out.println("added: "+peek_token().get_argument());
            argument_list.add(peek_token().get_argument());
            match(TokenType.ARGUMENT);
        }
        match(TokenType.EOL);
        commandType = CommandType.CMD_PLAY;
    }

    private void parse_pickperson() {
        match(TokenType.KW_PICKPERSON);
        match(TokenType.EOL);
        commandType = CommandType.CMD_PICKPERSON;
    }

    private void parse_leftorright() {
        match(TokenType.KW_LEFTORRIGHT);
        // We ignore the argument as we just want to display it how it already is in the message
        while (is_match(TokenType.ARGUMENT)) {
            match(TokenType.ARGUMENT);
        }
        match(TokenType.EOL);
        commandType = CommandType.CMD_LEFTORRIGHT;
    }

    private void parse_changelog() {
        match(TokenType.KW_CHANGELOG);

        match(TokenType.EOL);
        commandType = CommandType.CMD_CHANGELOG;
    }

    private void parse_queue_list() {
        System.out.println("Parsing queue list");
        match(TokenType.KW_LIST_QUEUE);
        match(TokenType.EOL);
        commandType = CommandType.CMD_QUEUELIST;
    }

    private void parse_stop() {
        System.out.println("parsing stop");
        match(TokenType.KW_STOP);
        match(TokenType.EOL);
        commandType = CommandType.CMD_STOP;
    }

    private void parse_cache() {
        match(TokenType.KW_CACHE);
        while (is_match(TokenType.ARGUMENT))
            argument_list.add(peek_token().get_argument());
        match(TokenType.EOL);
        commandType = CommandType.CMD_CACHE;
    }

    // KW_CERBERUS UserName(mentioned)
    private void parse_cerberus() {
        match(TokenType.KW_CERBERUS);
        if (is_match(TokenType.USER_MENTION)) {
            argument_list.add(peek_token().get_argument());
            match(TokenType.USER_MENTION);
        }
        // Optional grace period specifier
        if (is_match(TokenType.NUMERIC)) {
            match(TokenType.NUMERIC);
            argument_list.add(peek_token().get_argument());
        }
        match(TokenType.EOL);
        commandType = CommandType.CMD_CERBERUS;
    }

    private void parse_resume() {
        match(TokenType.KW_RESUME);
        // Nothing should appear after the resume keyword
        match(TokenType.EOL);
        commandType = CommandType.CMD_RESUME;
    }

    private void parse_pause() {
        match(TokenType.KW_PAUSE);
        // Nothing should appear after the pause keyword
        match(TokenType.EOL);
        commandType = CommandType.CMD_PAUSE;
    }

    public void parse_queue() {
        match(TokenType.KW_QUEUE);
        // If we match the play keyword, we can assume that
        // whatever follows is the name/identifier of something to play
        while (!is_match(TokenType.EOL)) {
            System.out.println("queue tokebn: "+peek_token().get_argument());
            argument_list.add(peek_token().get_argument());
            match(TokenType.ARGUMENT);
        }
        match(TokenType.EOL);
        commandType = CommandType.CMD_QUEUE;
    }

    public void parse_skip() {
        match(TokenType.KW_SKIP);

        match(TokenType.EOL);
        commandType = CommandType.CMD_SKIP;
    }

    // WE SHOULD TRACK ALL SONGS PLAYED AND THEN AT THE END OF WEEK/MONTH/YEAR SHOW MOST POPULAR SONGS!!!


    private Token peek_token() {
        if (stream_idx != stream_len)
            return stream.get(stream_idx);
        return new Token(TokenType.EOL, 0, null);
    }

    private void consume_next_token() {
        if (stream_idx != stream_len)
            stream_idx++;
    }

    private void match(TokenType expected) {
        System.out.println("Attempting match: "+expected.get_name());
        Token current_token = stream.get(stream_idx);

        // Cheat: If expected is of type TokenType.ARGUMENT,
        // we match whatever it is (except an end-of-line token)
        if (expected == TokenType.ARGUMENT && current_token.get_type() != TokenType.EOL) {
            stream_idx++;
            return ;
        }
        if (current_token.get_type() == expected) {
            stream_idx++;
            return ;
        }
        parse_success = false;
        System.out.println("MATCH ERROR");
    }

    private boolean is_match(TokenType expected) {
        if (stream_idx == stream_len)
            return false;
        Token current_token = stream.get(stream_idx);
        return expected == current_token.get_type();
    }


}

/*

-play youtubelink
-stop
-queue youtubelink
-pause
-perms modify Statement

 */