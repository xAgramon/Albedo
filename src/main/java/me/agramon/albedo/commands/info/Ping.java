package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Ping extends Command {

    public Ping() {
        super.name = "ping";
        super.help = "Not pong";
        super.cooldown = 5;
        super.category = new Category("Help/Info");
    }

    @Override
    protected void execute(CommandEvent e) {
        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setDescription("Ugh, fine. My ping is: **" + e.getJDA().getGatewayPing() + "ms!**");
        e.reply(eb.build());
    }
}
