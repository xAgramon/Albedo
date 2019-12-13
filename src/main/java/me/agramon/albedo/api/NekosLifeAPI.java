package me.agramon.albedo.api;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class NekosLifeAPI extends Command {

    private static final OkHttpClient client = new OkHttpClient();
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
