package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Feet extends Command {
    public Feet() {
        super.name = "feet";
        super.help = "Feet gang?";
        super.cooldown = 5;
        super.category = new Category("NSFW");
    }

    @Override
    protected void execute(CommandEvent e) {
        String url;

        int random = (int) (Math.random() * 2);

        if (e.getMessage().getTextChannel().isNSFW()) {
            if (e.getArgs().equalsIgnoreCase("gif")) {
                url = "https://neko.life/api/v2/img/feetg";
            } else if (random == 1) {
                url = "https://nekos.life/api/v2/img/erofeet";
            } else {
                url = "https://nekos.life/api/v2/img/feet";
            }
        } else {
            e.reply("This is not a NSFW channel!");
            return;
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
