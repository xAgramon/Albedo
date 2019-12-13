package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.NekosLifeAPI;

public class Pussy extends NekosLifeAPI {

    public Pussy() {
        super("pussy", "Cat", 5, "pussy");
    }

    @Override
    protected void execute(CommandEvent e) {
        if (e.getMessage().getTextChannel().isNSFW()) {
            super.execute(e);
        } else {
            e.reply("This is not a NSFW channel!");
        }
    }
}
