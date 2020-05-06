package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.ImageBoardAPI;

public class Gelbooru extends ImageBoardAPI {
    public Gelbooru() {
        super("gelbooru", "Random NSFW anime image", 0, "gelbooru");
        super.aliases = new String[]{"gb"};
        super.category = new Category("NSFW");
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
