package me.agramon.albedo.commands.nsfw;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Hentai extends Command {

    private static final OkHttpClient client = new OkHttpClient();

    public Hentai() {
        super.name = "hentai";
        super.help = "Random image of hentai ( ͡° ͜ʖ ͡°)";
        super.cooldown = 5;
        super.category = new Category("NSFW");
    }

    @Override
    protected void execute(CommandEvent e) {
        if (!e.getMessage().getTextChannel().isNSFW()) {
            e.reply("This is not a NSFW channel!");
            return;
        }

        String url;
        if (e.getArgs().equalsIgnoreCase("gif")) {
            url = "https://nekos.life/api/v2/img/Random_hentai_gif";
        } else {
            url = "https://nekos.life/api/v2/img/hentai";
        }

        String image = null;

        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (ResponseBody responseBody = response.body()) {
            if (!response.isSuccessful()) try {
                throw new IOException("Unexpected code " + response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                image = new JSONObject(Objects.requireNonNull(responseBody).string()).get("url").toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        MessageEmbed embed;
        embed = EmbedUtils.embedImage(image)
                .setColor(Color.MAGENTA)
                .build();
        e.reply(embed);
    }
}
