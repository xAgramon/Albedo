package me.agramon.albedo.commands.roleplay;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;

public class Slap extends PurrBotAPI {
    public Slap() {
        super("slap", "Slap someone", 5, "/sfw/slap/gif", "slaps you");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
