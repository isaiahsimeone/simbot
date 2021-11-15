package Commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.user.UserChangeSelfDeafenedListener;
import org.javacord.api.util.event.ListenerManager;
import simbot.SimPlayer;
import util.Utilities;

import java.util.Optional;

public class CerberusCmd {

    private static boolean is_monitoring = false;
    private static ListenerManager<UserChangeSelfDeafenedListener> deafened_listener;

    public static boolean execute(SimPlayer simplayer, DiscordApi api, String target_user) {
        if (!is_monitoring) {
            is_monitoring = true;
            MessageCreateEvent event = simplayer.getLastCmdMessage();
            Optional<ServerVoiceChannel> afk_channel = simplayer.getServerVoiceChannel().getServer().getAfkChannel();

            if (afk_channel.isEmpty())
                event.getMessage().getChannel().sendMessage("AFK channel doesn't exist");

            // Isolate which user is being monitored - get userid from command argument
            long user_id = Utilities.stripToLong(target_user);

            User target;
            try {
                System.out.println(api.getOwner().get());
                target = api.getUserById(user_id).get();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            deafened_listener = api.addUserChangeSelfDeafenedListener(deafened -> {
                System.out.println("Someone deafened");
                if (deafened.getUser().equals(target) && afk_channel.isPresent()) {
                    target.move(afk_channel.get());
                    System.out.println("THE TARGET DEAFENED");
                }
            });
        } else {
            // toggled, destroy listener
            deafened_listener.remove();
        }

        return true;
    }
}
