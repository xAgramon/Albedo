package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Ping extends Command {

    public Ping() {
        super.name = "ping";
        super.help = "Not pong";
        super.cooldown = 5;
        super.category = new Category("Help/Info");
    }

    @Override
    protected void execute(CommandEvent e) {
        e.reply("Ugh, fine. My ping is: **" + e.getJDA().getGatewayPing() + "ms!**");
    }
}
