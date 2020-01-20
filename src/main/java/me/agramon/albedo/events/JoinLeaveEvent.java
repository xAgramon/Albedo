package me.agramon.albedo.events;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;

import java.awt.*;

public class JoinLeaveEvent extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase(Config.getDB("DATABASE"));
        MongoCollection<Document> collection = db.getCollection(Config.getCol("COLLECTION"));

        Document user = new Document("UserID", e.getMember().getId());
        user.append("UserID", e.getMember().getId());
        user.append("Adores", 0);
        collection.insertOne(user);
    }

    public void onGuildMemberLeave(GuildMemberLeaveEvent e) {
        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase(Config.getDB("DATABASE"));
        MongoCollection<Document> collection = db.getCollection(Config.getCol("COLLECTION"));

        Document user = collection.find(new Document("UserID", e.getMember().getId())).first();
        Document toDelete = user;
        collection.deleteOne(toDelete);
    }
}
