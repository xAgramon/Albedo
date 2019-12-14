package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Blowjob extends Command {
    public Blowjob() {
        super.name = "blowjob";
        super.help = "O <==8";
        super.cooldown = 5;
        super.aliases = new String[]{"bj"};
        super.category = new Category("NSFW");
    }

    @Override
    protected void execute(CommandEvent e) {
        if (!e.getMessage().getTextChannel().isNSFW()) {
            e.reply("This is not a NSFW channel!");
            return;
        }

        String url;
        if (e.getArgs().equalsIgnoreCase("gif")) {
            url = "https://nekos.life/api/v2/img/bj";
        } else {
            url = "https://nekos.life/api/v2/img/blowjob";
        }

        WebUtils.ins.getJSONObject(url).async((json) -> {
            String image = json.get("url").asText();
            MessageEmbed embed;
            embed = EmbedUtils.embedImage(image)
                    .setColor(Color.MAGENTA)
                    .build();
            e.reply(embed);
        });
    }
}
