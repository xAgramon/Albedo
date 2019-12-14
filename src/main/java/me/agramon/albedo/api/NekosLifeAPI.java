package me.agramon.albedo.api;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class NekosLifeAPI extends Command {
    public String name;
    public String help;
    public int cooldown;
    public String api;

    public NekosLifeAPI(String name, String help, int cooldown, String api) {
        super.name = name;
        super.help = help;
        super.cooldown = cooldown;
        super.category = new Category("NSFW");
        this.api = api;
    }

    @Override
    protected void execute(CommandEvent e) {
        String url = "https://nekos.life/api/v2/img/" + api;

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
