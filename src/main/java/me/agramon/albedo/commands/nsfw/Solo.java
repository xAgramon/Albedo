package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Solo extends Command {
    public Solo() {
        super.name = "solo";
        super.help = "I love myself";
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
            if ((int)(Math.random() * 2) == 1) {
                url = "https://nekos.life/api/v2/img/solog";
            } else {
                url = "https://nekos.life/api/v2/img/pwankg";
            }
        } else {
            url = "https://nekos.life/api/v2/img/solo";
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
