package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;

public class Kitsune extends PurrBotAPI {
    public Kitsune() {
        super("kitsune", "Fox girls <3", 5, "/sfw/kitsune/img");
    }

    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}

