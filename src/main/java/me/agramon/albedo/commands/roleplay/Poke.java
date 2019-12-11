package me.agramon.albedo.commands.roleplay;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;

public class Poke extends PurrBotAPI {
    public Poke() {
        super("poke", "Poke someone", 5, "/sfw/poke/gif", "pokes you");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
