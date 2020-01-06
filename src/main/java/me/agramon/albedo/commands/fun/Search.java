package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.kodehawa.lib.imageboards.DefaultImageBoards;
import net.kodehawa.lib.imageboards.entities.BoardImage;

import java.awt.*;

public class Search extends Command {
    public Search () {
        super.name = "search";
        super.hidden = true;
        super.cooldown = 5;
        super.category = new Category("Fun");
        super.aliases = new String[]{"s"};
    }

    @Override
    protected void execute(CommandEvent e) {
        String temp = e.getArgs();
        String [] args = temp.split("\\s+");

        if (temp.isEmpty() || args.length != 2) {
            e.reply("The correct usage is >s <imageboard> <tag>");
            return;
        }

        String api = args[1];
        BoardImage image;

        try {
            if (args[0].equals("sb")) {
                image = DefaultImageBoards.SAFEBOORU.search(api).blocking().get((int) (Math.random() * 60));
            } else if (args[0].equals("db")) {
                if (!e.getMessage().getTextChannel().isNSFW()) {
                    e.reply("You perv! This is not a NSFW channel!");
                    return;
                }
                image = DefaultImageBoards.DANBOORU.search(api).blocking().get((int) (Math.random() * 60));
            } else if (args[0].equals("kc")) {
                if (!e.getMessage().getTextChannel().isNSFW()) {
                    e.reply("You perv! This is not a NSFW channel!");
                    return;
                }
                image = DefaultImageBoards.KONACHAN.search(api).blocking().get((int) (Math.random() * 60));
            } else if (args[0].equals("gb")) {
                if (!e.getMessage().getTextChannel().isNSFW()) {
                    e.reply("You perv! This is not a NSFW channel!");
                    return;
                }
                image = DefaultImageBoards.GELBOORU.search(api).blocking().get((int) (Math.random() * 60));
            } else if (args[0].equals("e621")) {
                if (!e.getMessage().getTextChannel().isNSFW()) {
                    e.reply("You perv! This is not a NSFW channel!");
                    return;
                }
                image = DefaultImageBoards.E621.search(api).blocking().get((int) (Math.random() * 60));
            } else if (args[0].equals("e926")) {
                image = DefaultImageBoards.E926.search(api).blocking().get((int) (Math.random() * 60));
            } else if (args[0].equals("rule34")) {
                if (!e.getMessage().getTextChannel().isNSFW()) {
                    e.reply("You perv! This is not a NSFW channel!");
                    return;
                }
                image = DefaultImageBoards.RULE34.search(api).blocking().get((int) (Math.random() * 60));
            } else if (args[0].equals("yandere")) {
                if (!e.getMessage().getTextChannel().isNSFW()) {
                    e.reply("You perv! This is not a NSFW channel!");
                    return;
                }
                image = DefaultImageBoards.YANDERE.search(api).blocking().get((int) (Math.random() * 60));
            } else {
                e.reply("Invalid imageboard! Please try <sb/db/kc/gb/e621/e926/rule34/yandere>");
                return;
            }
        } catch (Exception ex) {
            e.reply("Something went wrong!");
            return;
        }

        MessageEmbed embed = EmbedUtils.embedImage(image.getURL())
                .setColor(Color.MAGENTA)
                .build();
        e.reply(embed);
    }
}
