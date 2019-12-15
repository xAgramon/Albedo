package me.agramon.albedo.commands.economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bson.Document;

import java.awt.*;

public class Daily extends Command {
    public Daily() {
        super.name = "daily";
        super.help = "Daily dose of credits :)";
        super.cooldown = 86400;
        super.category = new Category("Economy");
    }

    @Override
    public String getCooldownError(CommandEvent e, int seconds) {
        long numberOfHours = seconds / (60 * 60);
        long numberOfMinutes = (seconds / 60) - (numberOfHours * 60);
        long numberOfSeconds = seconds % 60;

        EmbedBuilder eb = new EmbedBuilder()
                .setDescription("You can claim your daily again in ``" + numberOfHours + " hours, " + numberOfMinutes + " minutes, " + numberOfSeconds + " seconds``")
                .setColor(Color.MAGENTA);
        e.reply(eb.build());
        return "";
    }

    protected void execute(CommandEvent e) {
        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        String user = e.getAuthor().getId();

        Document found = collection.find(new Document("UserID", user)).first();

        int credits = (Integer) found.get("Credits");
        int newCredits = credits + 100;

        Document query = new Document();
        query.append("UserID", user);
        Document setData = new Document();
        setData.append("Credits", newCredits);
        Document update = new Document();
        update.append("$set", setData);
        collection.updateOne(query, update);

        EmbedBuilder eb = new EmbedBuilder()
                .setDescription("You now have " + newCredits + " credits!")
                .setColor(Color.MAGENTA);
        e.reply(eb.build());
    }
}
