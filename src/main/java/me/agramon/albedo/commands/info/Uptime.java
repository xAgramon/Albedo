package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class Uptime extends Command {
    public Uptime() {
        super.name = "uptime";
        super.help = "Shows the uptime of the bot";
        super.cooldown = 5;
        super.category = new Category("Help/Info");
    }

    @Override
    protected void execute(CommandEvent e) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long uptime = runtimeMXBean.getUptime();
        long uptimeInSeconds = uptime / 1000;
        long numberOfHours = uptimeInSeconds / (60 * 60);
        long numberOfMinutes = (uptimeInSeconds / 60) - (numberOfHours * 60);
        long numberOfSeconds = uptimeInSeconds % 60;

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setDescription("Albedo has been up for ``" + numberOfHours + " hours, " + numberOfMinutes + " minutes, " + numberOfSeconds + " seconds``");

        e.reply(eb.build());
    }
}
