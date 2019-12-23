package me.agramon.albedo.events;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;

public class Credits extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot() || e.getMessage().getContentRaw().charAt(0) == '>' || e.getMessage().getContentRaw().charAt(0) == '!' || e.getMessage().getContentRaw().charAt(0) == '$') {
            return;
        }

        String URI = Config.getURI("URI");
        MongoClient mc = MongoClients.create(URI);
        MongoDatabase mb = mc.getDatabase("Albedo");
        MongoCollection<Document> collection = mb.getCollection("Anime Argonauts");

        User user = e.getAuthor();

        Document found = collection.find(new Document("UserID", user.getId())).first();
        if (found != null) {
            Document query = new Document();
            query.append("UserID", found.get("UserID"));

            Document setData = new Document();
            int oldCredits = (Integer) (found.get("XP"));
            int randomCredits = (int) (Math.random() * 10);
            setData.append("Credits", oldCredits + randomCredits);

            Document update = new Document();
            update.append("$set", setData);

            collection.updateOne(query, update);
        }

    }
}
