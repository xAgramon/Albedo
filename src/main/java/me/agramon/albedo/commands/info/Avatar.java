package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

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
        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA);
        if (e.getMessage().getMentionedMembers().size() != 0) {
            eb.setTitle(e.getMessage().getMentionedMembers().get(0).getUser().getName() + "'s Avatar");
            eb.setImage(e.getMessage().getMentionedMembers().get(0).getUser().getAvatarUrl());
        } else if (!e.getArgs().isEmpty() && e.getJDA().getUserById(e.getArgs()) != null) {
            eb.setTitle(e.getJDA().getUserById(e.getArgs()).getName() + "'s Avatar");
            eb.setImage(e.getJDA().getUserById(e.getArgs()).getAvatarUrl());
        } else {
            eb.setTitle(e.getMessage().getMember().getUser().getName() + "'s Avatar");
            eb.setImage(e.getMessage().getMember().getUser().getAvatarUrl());
        }
        e.reply(eb.build());
    }
}
