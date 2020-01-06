package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Kitsune extends Command {

    public Kitsune() {
        super.name = "kitsune";
        super.help = "Random image of fox girls <3";
        super.cooldown = 5;
        super.category = new Category("Fun");
    }

    protected void execute(CommandEvent e) {
        String url;
        if (e.getMessage().getTextChannel().isNSFW()) {
            url = "https://nekos.life/api/v2/img/erok";
        } else {
            url = "https://nekos.life/api/v2/img/fox_girl";
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

