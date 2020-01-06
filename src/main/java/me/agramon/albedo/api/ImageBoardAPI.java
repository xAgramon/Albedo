package me.agramon.albedo.api;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.kodehawa.lib.imageboards.DefaultImageBoards;
import net.kodehawa.lib.imageboards.entities.BoardImage;

import java.awt.*;

public class ImageBoardAPI extends Command {
    public String name;
    public String help;
    public int cooldown;
    public String imageBoard;
    public String api;

    // Specific tag from an ImageBoard
    public ImageBoardAPI(String name, String help, int cooldown, String imageBoard, String api) {
        super.name = name;
        super.help = help;
        super.cooldown = cooldown;
        super.category = new Category("Fun");
        this.imageBoard = imageBoard;
        this.api = api;
    }

    // Random image from an ImageBoard
    public ImageBoardAPI(String name, String help, int cooldown, String imageboard) {
        super.name = name;
        super.help = help;
        super.cooldown = cooldown;
        this.imageBoard = imageboard;
    }

    @Override
    protected void execute(CommandEvent e) {

        BoardImage image;

        if (api != null) {
            if (imageBoard.equals("safebooru")) {
                image = DefaultImageBoards.SAFEBOORU.search(api).blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("danbooru")) {
                image = DefaultImageBoards.DANBOORU.search(api).blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("konachan")) {
                image = DefaultImageBoards.KONACHAN.search(api).blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("gelbooru")) {
                image = DefaultImageBoards.GELBOORU.search(api).blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("e621")) {
                image = DefaultImageBoards.E621.search(api).blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("e926")) {
                image = DefaultImageBoards.E926.search(api).blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("rule34")) {
                image = DefaultImageBoards.RULE34.search(api).blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("yandere")) {
                image = DefaultImageBoards.YANDERE.search(api).blocking().get((int) (Math.random() * 60));
            } else {
                image = DefaultImageBoards.SAFEBOORU.search(api).blocking().get((int) (Math.random() * 60));
            }
        } else {
            if (imageBoard.equals("safebooru")) {
                image = DefaultImageBoards.SAFEBOORU.get().blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("danbooru")) {
                image = DefaultImageBoards.DANBOORU.get().blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("konachan")) {
                image = DefaultImageBoards.KONACHAN.get().blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("gelbooru")) {
                image = DefaultImageBoards.GELBOORU.get().blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("e621")) {
                image = DefaultImageBoards.E621.get().blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("e926")) {
                image = DefaultImageBoards.E926.get().blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("rule34")) {
                image = DefaultImageBoards.RULE34.get().blocking().get((int) (Math.random() * 60));
            } else if (imageBoard.equals("yandere")) {
                image = DefaultImageBoards.YANDERE.get().blocking().get((int) (Math.random() * 60));
            } else {
                image = DefaultImageBoards.SAFEBOORU.get().blocking().get((int) (Math.random() * 60));
            }
        }
        MessageEmbed embed = EmbedUtils.embedImage(image.getURL())
                .setColor(Color.MAGENTA)
                .build();
        e.reply(embed);
    }
}
