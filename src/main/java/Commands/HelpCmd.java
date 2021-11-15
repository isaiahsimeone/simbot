package Commands;

import simbot.SimPlayer;

public class HelpCmd implements Command {
    private SimPlayer simplayer;

    public HelpCmd(SimPlayer simplayer) {
        this.simplayer = simplayer;
    }

    @Override
    public boolean execute() {
        // Output help info
        return true;
    }

    @Override
    public String get_command_name() {
        return "help";
    }
}
