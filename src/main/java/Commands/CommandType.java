package Commands;
public enum CommandType {
    CMD_PLAY(0),
    CMD_STOP(1),
    CMD_PAUSE(2),
    CMD_RESUME(3),
    CMD_SKIP(4),
    CMD_QUEUE(5),
    CMD_CERBERUS(6),
    CMD_CACHE(7);

    public final int commandType;

    CommandType(int commandType) {
        this.commandType = commandType;
    }
}
