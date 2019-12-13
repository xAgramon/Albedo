package me.agramon.albedo.commands.economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.*;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

import java.util.ArrayList;
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
                User u = e.getJDA().getUserById(document.get("UserID").toString());
                name.add(u.getAsTag());
                adores.add((Integer) document.get("Adores"));
                credits.add((Integer) document.get("Credits"));
            }
        } finally {
            cursor.close();
        }
    }
}
