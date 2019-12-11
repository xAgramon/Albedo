package me.agramon.albedo.events;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;

public class UserCreateDB extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        String URI = Config.getURI("URI");
        MongoClient mc = MongoClients.create(URI);
        MongoDatabase mb = mc.getDatabase("Albedo");
        MongoCollection<Document> collection = mb.getCollection("Adores");

        Document user = new Document("UserID", e.getMember().getId());
        user.append("UserID", e.getMember().getId());
        user.append("Adores", 0);
        collection.insertOne(user);
    }

    public void onGuildMemberLeave(GuildMemberLeaveEvent e) {
        String URI = Config.getURI("URI");
        MongoClient mc = MongoClients.create(URI);
        MongoDatabase mb = mc.getDatabase("Albedo");
        MongoCollection<Document> collection = mb.getCollection("Adores");

        Document user = collection.find(new Document("UserID", e.getMember().getId())).first();
        collection.deleteOne(user);
    }
}
