package me.agramon.albedo.api;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class PurrBotAPI extends Command {

    public String name;
    public String help;
    public int cooldown;
    public String api;
    public String title;

    public PurrBotAPI (String name, String help, int cooldown, String api) {
        super.name = name;
        super.help = help;
        super.cooldown = cooldown;
        super.category = new Category("Fun");
        this.api = api;
    }

    // Roleplay Constructor
    public PurrBotAPI (String name, String help, int cooldown, String api, String title) {
        super.name = name;
        super.help = help;
        super.cooldown = cooldown;
        super.category = new Category("Roleplay");
        this.api = api;
        this.title = title;
    }

    @Override
    protected void execute(CommandEvent e) {
        WebUtils.ins.getJSONObject("https://purrbot.site/api/img" + api).async((json) -> {
            String url = json.get("link").asText();
            MessageEmbed embed;
            if (e.getArgs() != "" && e.getMessage().getMentionedMembers().get(0).getUser() == e.getSelfUser()) {
                embed = EmbedUtils.embedImage(url)
                        .setColor(Color.MAGENTA)
                        .setTitle(e.getMessage().getMentionedMembers().get(0).getUser().getName() + " loves you too " + e.getMessage().getAuthor().getName() + "!")
                        .build();
            } else if (e.getArgs() != "" && e.getMessage().getMentionedMembers().get(0) != null) {
                embed = EmbedUtils.embedImage(url)
                        .setColor(Color.MAGENTA)
                        .setTitle(e.getMessage().getAuthor().getName() + " " + title + " " + e.getMessage().getMentionedMembers().get(0).getUser().getName())
                        .build();
            } else {
                embed = EmbedUtils.embedImage(url)
                        .setColor(Color.MAGENTA)
                        .build();
            }
            e.reply(embed);
        });
    }
}