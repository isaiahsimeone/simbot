package Commands;

import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import simbot.SimPlayer;

import java.util.ArrayList;
import java.util.Random;

public class PickPersonCmd {

    public static boolean execute(SimPlayer simplayer, ServerVoiceChannel vc) {

        // TEMPORARY
        simplayer.getLastCmdMessage().getChannel().sendMessage("No worky atm... Fuck off");
        if (1==1) return false;

        if (vc.getConnectedUsers().isEmpty())
            return false;

        ArrayList<User> user_list = new ArrayList<>(vc.getConnectedUsers());

        if (user_list.isEmpty())
            return false;

        int rand_user_idx;
        // Never pick a bot (including ourselves)
        do {
            rand_user_idx = dice_roll(user_list.size());
            System.out.println("Skipping bot");
        } while (user_list.get(rand_user_idx).isBot());

        simplayer.getLastCmdMessage().getChannel().sendMessage("I pick " + user_list.get(rand_user_idx).getDiscriminatedName());

        return true;
    }


    private static int dice_roll(int faces) {
        Random random = new Random();
        return random.nextInt(faces);
    }

}
