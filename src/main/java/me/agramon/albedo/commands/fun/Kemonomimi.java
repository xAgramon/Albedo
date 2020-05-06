package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Kemonomimi extends Command {
    public Kemonomimi() {
        super.name = "kemonomimi";
        super.aliases = new String[]{"kemo", "kemono"};
        super.help = "Random image of cute animal-eared girls <3";
        super.cooldown = 3;
        super.category = new Category("Fun");
    }

    @Override
    protected void execute(CommandEvent e) {
        String url;
        if (e.getMessage().getTextChannel().isNSFW()) {
            if ((int)(Math.random() * 2) == 1) {
                url = "https://nekos.life/api/v2/img/lewdkemo";
            } else {
                url = "https://nekos.life/api/v2/img/erokemo";
            }
        } else {
            url = "https://nekos.life/api/v2/img/kemonomimi";
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
