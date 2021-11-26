package Commands;

import simbot.SimPlayer;

public class HelpCmd {
    private SimPlayer simplayer;

    public HelpCmd(SimPlayer simplayer) {
        this.simplayer = simplayer;
    }


    public boolean execute() {
        // Output help info
        return true;
    }

}
