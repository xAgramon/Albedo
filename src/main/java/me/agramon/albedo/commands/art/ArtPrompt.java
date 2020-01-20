package me.agramon.albedo.commands.art;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.IOException;

public class ArtPrompt extends Command {
    public ArtPrompt() {
        super.name = "artprompt";
        super.help = "Gives you a random art prompt to draw";
        super.cooldown = 3;
        super.category = new Category("Art");
        super.aliases = new String[] {"aab", "ap", "art", "prompt"};
    }

    @Override
    protected void execute(CommandEvent e) {
        String url;
        if (e.getArgs().isEmpty()) {
            e.reply("The correct usage is: >ap <character/creature/environment/fantasy>");
            return;
        } else if (e.getArgs().equals("character")) {
            url = "https://artprompts.org/character/";
        } else if (e.getArgs().equals("creature")) {
            url = "https://artprompts.org/creature";
        } else if (e.getArgs().equals("environment")) {
            url = "https://artprompts.org/environment";
        } else if (e.getArgs().equals("fantasy")) {
            url = "https://www.magatsu.net/generators/fantasy-art/index.php";
        } else {
            e.reply("The correct usage is: >ap <character/creature/environment/fantasy>");
            return;
        }

        String prompt;

        String html = null;
        try {
            html = Jsoup.connect(url).get().html();
        } catch (IOException ex) {}
        Document doc = Jsoup.parse(html);
        String text = doc.body().text();

        if (e.getArgs().equals("fantasy")) {
            prompt = text.substring(text.indexOf("Your prompt: ") + 13, text.indexOf("Find more"));
        } else {
            prompt = text.substring(text.indexOf("!") + 1, text.indexOf("Submit"));
        }

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setDescription("Your art prompt is: " + prompt);
        e.reply(eb.build());
    }
}
