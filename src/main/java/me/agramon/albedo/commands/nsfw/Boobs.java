package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Boobs extends Command {
    public Boobs() {
        super.name = "boobs";
        super.help = "O.O";
        super.cooldown = 5;
        super.category = new Category("NSFW");
        super.aliases = new String[]{"tits", "oppai"};
    }

    @Override
    protected void execute(CommandEvent e) {
        if (!e.getMessage().getTextChannel().isNSFW()) {
            e.reply("This is not a NSFW channel!");
            return;
        }

        String url;
        if (e.getArgs().equalsIgnoreCase("gif")) {
            url = "https://nekos.life/api/v2/img/boobs";
        } else {
            url = "https://nekos.life/api/v2/img/tits";
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
