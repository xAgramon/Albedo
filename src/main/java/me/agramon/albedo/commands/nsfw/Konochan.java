package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.ImageBoardAPI;

public class Konochan extends ImageBoardAPI {
    public Konochan() {
        super("konochan", "Random NSFW anime wallpaper", 0, "konochan");
        super.aliases = new String[]{"kc"};
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
