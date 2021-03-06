package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;

public class Help extends Command {
    public Help() {
        super.name = "help";
        super.cooldown = 3;
        super.help = "Command list";
        super.aliases = new String[]{"cmds", "commands"};
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
                                "• avatar (user) \n" +
                                "• info \n" +
                                "• ping \n" +
                                "• profile (user) \n" +
                                "• uptime \n"
                        , true)

                .addField(":art: Art :art:",
                        "• artprompt <category> \n"
                        , true)

                .addField(":video_game: Fun :video_game:",
                        "• albedo (gif) \n" +
                                "• baka \n" +
                                "• bowsette \n" +
                                "• e926 \n" +
                                "• horns \n" +
                                "• kemonomimi \n" +
                                "• kitsune \n" +
                                "• moescape \n" +
                                "• neko <gif> \n" +
                                "• paste <message> \n" +
                                "• safebooru \n" +
                                "• smug \n"
                        , true)

                .addField(":performing_arts: Roleplay :performing_arts:",
                        "• cuddle (user) \n" +
                                "• kiss (user) \n" +
                                "• lick (user) \n" +
                                "• pat (user) \n" +
                                "• poke (user) \n" +
                                "• slap (user) \n" +
                                "• tickle (user) \n"
                        , true)

                .addField(":underage: NSFW :underage:",
                        "• anal \n" +
                                "• blowjob (gif) \n" +
                                "• boobs \n" +
                                "• cum \n" +
                                "• danbooru \n" +
                                "• e621 \n" +
                                "• feet \n" +
                                "• femdom \n" +
                                "• gelbooru \n" +
                                "• hentai (gif) \n" +
                                "• kemonomimi \n" +
                                "• kitsune \n" +
                                "• konochan \n" +
                                "• lewd \n" +
                                "• neko (gif) \n" +
                                "• pussy \n" +
                                "• rule34 \n" +
                                "• solo (gif)\n" +
                                "• trap \n" +
                                "• vanilla \n" +
                                "• yandere \n" +
                                "• yuri \n"
                        , true)

                .addField(":pencil: Administration :pencil:",
                        "• chatclear <#> \n" +
                                "• getid <#> \n"
                        , true);

        e.reply(eb.build());
    }
}
