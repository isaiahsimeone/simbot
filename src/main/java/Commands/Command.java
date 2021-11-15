package Commands;

import java.util.ArrayList;


public interface Command {

    public boolean execute();
    public String get_command_name();
}
