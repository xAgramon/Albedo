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
                                "• ping \n" +
                                "• profile <user> \n" +
                                "• uptime \n"
                        , true)

                .addField(":video_game: Fun :video_game:",
                        "• albedo <gif> \n" +
                                "• baka \n" +
                                "• bowsette \n" +
                                "• e926 \n" +
                                "• horns \n" +
                                "• kitsune \n" +
                                "• neko \n" +
                                "• paste <message> \n" +
                                "• safebooru \n"
                        , true)

                .addField(":moneybag: Economy :moneybag:",
                        "• balance <user> \n" +
                                "• daily \n"
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
                        "• anal \n" +
                                "• blowjob <gif> \n" +
                                "• boobs \n" +
                                "• cum \n" +
                                "• danbooru \n" +
                                "• e621 \n" +
                                "• feet \n" +
                                "• femdom \n" +
                                "• gelbooru \n" +
                                "• hentai <gif> \n" +
                                "• kitsune \n" +
                                "• konochan \n" +
                                "• neko \n" +
                                "• pussy \n" +
                                "• rule34 \n" +
                                "• solo <gif>\n" +
                                "• trap \n" +
                                "• vanilla \n" +
                                "• yandere \n"
                        , true)

                .addField(":pencil: Administration :pencil:",
                        "• addusers \n" +
                                "• chatclear <#> \n" +
                                "• setadores <user> <#> \n" +
                                "• setcredits <user> <#> \n"
                        , true);

        e.reply(eb.build());

        EmbedBuilder eb2 = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setTitle("Features")

                .addField(":exclamation: Features :exclamation:",
                        "➢ Reacts to submissions in art channels \n" +
                                "➢ Keeps track of 'adores' each submission gets in your profile \n" +
                                "➢ Economy system with credits (shop soon to come!) \n"
                        , true);
        e.reply(eb2.build());
    }
}
