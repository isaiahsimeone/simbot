package Commands;
public enum CommandType {
    CMD_PLAY(0),
    CMD_STOP(1),
    CMD_PAUSE(2),
    CMD_RESUME(3),
    CMD_SKIP(4),
    CMD_QUEUE(5),
    CMD_CERBERUS(6),
    CMD_CACHE(7),
    CMD_QUEUELIST(8),
    CMD_CHANGELOG(9),
    CMD_LEFTORRIGHT(10),
    CMD_PICKPERSON(11),
    CMD_FASTFORWARD(12),
    CMD_GETPLAYING(13),
    CMD_REWIND(14);

    public final int commandType;

    CommandType(int commandType) {
        this.commandType = commandType;
    }
}
