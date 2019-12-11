package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.ImageBoardAPI;

public class Rule34 extends ImageBoardAPI {
    public Rule34() {
        super("rule34", "Hmm...", 5, "rule34");
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
