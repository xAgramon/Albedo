package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;
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
        String api;
        if (e.getMessage().getTextChannel().isNSFW()) {
            api = "/nsfw/neko/img";
        } else {
            api = "/sfw/neko/img";
        }

        WebUtils.ins.getJSONObject("https://purrbot.site/api/img" + api).async((json) -> {
            String url = json.get("link").asText();
            MessageEmbed embed;
            embed = EmbedUtils.embedImage(url)
                .setColor(Color.MAGENTA)
                .build();
            e.reply(embed);
        });
    }
}
