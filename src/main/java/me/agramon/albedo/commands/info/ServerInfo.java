package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ServerInfo extends Command {
    public ServerInfo() {
        super.name = "serverinfo";
        super.help = "Shows the server info";
        super.category = new Category("Help/Info");
        super.cooldown = 5;
        super.aliases = new String[]{"info"};
    }

    @Override
    protected void execute(CommandEvent e) {
        Guild g = e.getGuild();

        String generalInfo = "**Owner**: " + g.getOwner().getUser().getName() + "\n**Region**: " + g.getRegion().getName() + "\n**Birthday** :birthday:: " + g.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String memberInfo = "**Total Members**: " + g.getMemberCount();

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setThumbnail(g.getIconUrl())
                .setTitle("Server Info for " + g.getName())
                .addField("General Info", generalInfo, false)
                .addField("Member Info", memberInfo, false);

        e.reply(eb.build());
    }
}
