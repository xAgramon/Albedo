package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import sun.awt.EmbeddedFrame;

import java.awt.*;

public class Avatar extends Command {
    public Avatar() {
        super.name = "avatar";
        super.help = "Shows you a user's avatar";
        super.cooldown = 5;
        super.category = new Category("Help/Info");
    }

    @Override
    protected void execute(CommandEvent e) {
        if (e.getMessage().getMentionedMembers().get(0) == null) {
            e.reply("Please give a user to get the avatar from!");
            return;
        }
        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setTitle(e.getMessage().getMentionedMembers().get(0).getUser().getName() + "'s Avatar")
                .setImage(e.getMessage().getMentionedMembers().get(0).getUser().getAvatarUrl());
        e.reply(eb.build());
    }
}
