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
            e.reply("The correct usage is: >ap <character/catgirl/magicalgirl>");
            return;
        } else if (e.getArgs().equals("character")) {
            url = "https://www.seventhsanctum.com/generate.php?Genname=generalperson";
        } else if (e.getArgs().equals("catgirl")) {
            url = "https://www.seventhsanctum.com/generate.php?Genname=catgirl";
        } else if (e.getArgs().equals("magicalgirl")) {
            url = "https://www.seventhsanctum.com/generate.php?Genname=magicalgirl";
        } else {
            e.reply("The correct usage is: >ap <character/catgirl/magicalgirl>");
            return;
        }

        String prompt;

        String html = null;
        try {
            html = Jsoup.connect(url).get().html();
        } catch (IOException ex) {}
        Document doc = Jsoup.parse(html);
        String text = doc.body().text();

        if (e.getArgs().equals("character")) {
            prompt = text.substring(1055, text.indexOf(" This", 1055));
        } else if (e.getArgs().equals("catgirl")) {
            prompt = text.substring(958, text.indexOf(" This", 958));
        } else if (e.getArgs().equals("magicalgirl")){
            prompt = text.substring(937, text.indexOf(" This", 937));
        } else {
            e.reply("The correct usage is: >ap <character/catgirl/magicalgirl>");
            return;
        }

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setDescription(prompt);
        e.reply(eb.build());
    }
}
