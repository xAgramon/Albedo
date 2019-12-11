package me.agramon.albedo.commands.roleplay;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;

public class Tickle extends PurrBotAPI {
    public Tickle() {
        super("tickle", "Tickle someone", 5, "/sfw/tickle/gif", "tickles you");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
