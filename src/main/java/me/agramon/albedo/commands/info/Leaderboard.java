package me.agramon.albedo.commands.info;

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
import java.util.Comparator;
import java.util.List;

public class Leaderboard extends Command {
    public Leaderboard() {
        super.name = "leaderboard";
        super.cooldown = 5;
        super.category = new Category("Help/Info");
        super.aliases = new String[]{"lb"};
        super.help = "The top weebs of the server";
    }

    @Override
    protected void execute(CommandEvent e) {

        if (e.getArgs().isEmpty()) {
            e.reply("The correct usage is >lb <adore>");
            return;
        }

        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();

        List<Document> list = new ArrayList<>();
        
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                list.add(document);
            }
        } finally {
            cursor.close();
        }

        if (e.getArgs().equalsIgnoreCase("adores")) {

            Collections.sort(list, compareByAdores.reversed());

            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(Color.MAGENTA)
                    .setTitle("Top Artists 🎨")
                    .setThumbnail(e.getJDA().getUserById(list.get(0).get("UserID").toString()).getAvatarUrl());

            for (int i = 0; i < 10; i++) {
                User user = e.getJDA().getUserById(list.get(i).get("UserID").toString());
                eb.addField("", "**" + (i+1) + ".** " + user.getAsTag() + " - ``" + list.get(i).get("Adores") + "``", false);
            }

            e.reply(eb.build());

        } else {
            e.reply("That is not a valid category! Please try >lb <adores>");
        }
    }

    Comparator<Document> compareByAdores = new Comparator<Document>() {
        @Override
        public int compare(Document o1, Document o2) {
            return ((Integer)o1.get("Adores")).compareTo((Integer)(o2.get("Adores")));
        }
    };
}
