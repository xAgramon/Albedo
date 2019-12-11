package me.agramon.albedo.commands.roleplay;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;

public class Cuddle extends PurrBotAPI {
    public Cuddle() {
        super("cuddle", "Cuddle with someone owo", 5, "/sfw/cuddle/gif", "cuddles with you");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
