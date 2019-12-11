package me.agramon.albedo.commands.roleplay;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;

public class Kiss extends PurrBotAPI {
    public Kiss() {
        super("kiss", "Kiss someone ;)", 5, "/sfw/kiss/gif", "kisses you");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
