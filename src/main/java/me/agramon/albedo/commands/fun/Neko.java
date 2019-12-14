package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Neko extends Command {
    public Neko() {
        super.name = "neko";
        super.help = "Cat girls <3";
        super.cooldown = 5;
        super.category = new Category("Fun");
    }

    protected void execute(CommandEvent e) {
        String url;
        if (e.getMessage().getTextChannel().isNSFW()) {
            if (e.getArgs().equalsIgnoreCase("gif")) {
                url = "https://nekos.life/api/v2/img/nsfw_neko_gif";
            } else {
                url = "https://nekos.life/api/v2/img/lewd";
            }
        } else {
            if (e.getArgs().equalsIgnoreCase("gif")) {
                url = "https://nekos.life/api/v2/img/ngif";
            } else {
                url = "https://nekos.life/api/v2/img/neko";
            }
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
