package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Ping extends Command {

    public Ping() {
        super.name = "ping";
        super.help = "Not pong";
        super.cooldown = 10;
        super.category = new Category("Fun");
    }

    @Override
    protected void execute(CommandEvent e) {
        e.reply("Ugh, fine. My ping is: **" + e.getJDA().getGatewayPing() + "ms!**");
    }
}
