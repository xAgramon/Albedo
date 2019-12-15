package me.agramon.albedo.commands.economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.*;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard extends Command {
    public Leaderboard() {
        super.name = "leaderboard";
        super.cooldown = 5;
        super.category = new Category("Economy");
        super.aliases = new String[]{"lb"};
        super.help = "The top weebs of the server";
    }

    public List<String> name = new ArrayList<>();
    public List<Integer> adores = new ArrayList<>();
    public List<Integer> credits = new ArrayList<>();

    @Override
    protected void execute(CommandEvent e) {

        if (e.getArgs().isEmpty()) {
            e.reply("Invalid arguments! The correct usage is >lb <adores/credits>");
            return;
        }

        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                name.add(document.get("UserID").toString());
                adores.add((Integer) document.get("Adores"));
                credits.add((Integer) document.get("Credits"));
            }
        } finally {
            cursor.close();
        }

        int index;
        if (e.getArgs().equalsIgnoreCase("adores")) {
            index = adores.indexOf(Collections.max(adores));

            User user = e.getJDA().getUserById(name.get(index));
            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(Color.MAGENTA)
                    .setThumbnail(user.getAvatarUrl())
                    .setTitle("Top Artist - " + user.getName())
                    .setDescription(user.getName() + " has the most amount of adores on the server with **" + adores.get(index) + "** adores!");
            e.reply(eb.build());

        } else if (e.getArgs().equalsIgnoreCase("credits")) {
            index = credits.indexOf(Collections.max(credits));

            User user = e.getJDA().getUserById(name.get(index));
            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(Color.MAGENTA)
                    .setThumbnail(user.getAvatarUrl())
                    .setTitle("Richest Weeb - " + user.getName())
                    .setDescription(user.getName() + " has the most amount of credits on the server with **" + credits.get(index) + "** credits!");
            e.reply(eb.build());

        } else {
            e.reply("That is not a valid category! Please try >lb <adores/credits>");
        }

    }
}
