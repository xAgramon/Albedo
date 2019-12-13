package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.NekosLifeAPI;

public class Anal extends NekosLifeAPI {

    public Anal() {
        super("anal", "Another hole", 5, "anal");
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
