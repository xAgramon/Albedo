package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class Help extends Command {
    public Help() {
        super.name = "help";
        super.cooldown = 5;
        super.help = "Command list";
        super.category = new Category("Help/Info");
    }

    @Override
    protected void execute(CommandEvent e) {
        Guild g = e.getGuild();

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setThumbnail(g.getIconUrl())
                .setTitle("Available Commands")

                .addField(":question: Help/Info :question:",
                        "• help \n" +
                                "• dmhelp \n" +
                                "• avatar <user> \n" +
                                "• info \n" +
                                "• profile <user> \n" +
                                "• uptime \n"
                        , true)

                .addField(":video_game: Fun :video_game:",
                        "• albedo <gif> \n" +
                                "• bowsette \n" +
                                "• e926 \n" +
                                "• horns \n" +
                                "• kitsune \n" +
                                "• neko \n" +
                                "• paste <message> \n" +
                                "• ping \n" +
                                "• safebooru \n" +
                                "• search <board> <tag> \n"
                        , true)

                .addField(":performing_arts: Roleplay :performing_arts:",
                        "• cuddle <user> \n" +
                                "• kiss <user> \n" +
                                "• lick <user> \n" +
                                "• pat <user> \n" +
                                "• poke <user> \n" +
                                "• slap <user> \n" +
                                "• tickle <user> \n"
                        , true)

                .addField(":underage: NSFW :underage:",
                        "• danbooru \n" +
                                "• e621 \n" +
                                "• gelbooru \n" +
                                "• konochan \n" +
                                "• neko \n" +
                                "• rule34 \n" +
                                "• yandere \n"
                        , true)

                .addField(":pencil: Administration :pencil:",
                        "• addusers \n" +
                                "• chatclear <#> \n" +
                                "• setadores <#> \n"
                        , true);

        e.reply(eb.build());

        EmbedBuilder eb2 = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setTitle("Features")

                .addField(":exclamation: Features :exclamation:",
                        "➢ Reacts to submissions in art channels \n" +
                                "➢ Keeps track of 'adores' each submission gets in your profile"
                        , true);
        e.reply(eb2.build());
    }
}
