package me.agramon.albedo.commands.roleplay;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;

public class Lick extends PurrBotAPI {
    public Lick() {
        super("lick", "Lick someone", 5, "/sfw/lick/gif", "licks you");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
