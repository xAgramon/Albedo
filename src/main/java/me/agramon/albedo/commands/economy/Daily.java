package me.agramon.albedo.commands.economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import org.bson.Document;

public class Daily extends Command {
    public Daily() {
        super.name = "daily";
        super.help = "Daily dose of credits :)";
        super.cooldown = 86400;
    }

    @Override
    public String getCooldownError(CommandEvent e, int seconds) {
        long numberOfHours = seconds / (60 * 60);
        long numberOfMinutes = (seconds / 60) - (numberOfHours * 60);
        long numberOfSeconds = seconds % 60;

        e.getChannel().sendMessageFormat(
                "You can claim your daily again in `%s hours, %s minutes, %s seconds`",
                numberOfHours, numberOfMinutes, numberOfSeconds
        ).queue();
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
        e.reply("```You now have " + newCredits + " credits!```");
    }
}
