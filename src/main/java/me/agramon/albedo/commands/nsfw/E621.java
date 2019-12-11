package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.ImageBoardAPI;

public class E621 extends ImageBoardAPI {
    public E621() {
        super("e621", "Random NSFW furry image", 5, "e621");
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
