package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.NekosLifeAPI;

public class Vanilla extends NekosLifeAPI {

    public Vanilla() {
        super("vanilla", "The kind that the church approves", 5, "classic");
        super.aliases = new String[]{"sex"};
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
