package me.agramon.albedo.events;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;

public class ArtReaction extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()) { return; }

        String channel = e.getMessage().getChannel().getName();
        if ((channel.equals("completed-art") || channel.equals("work-in-progress") || channel.equals("nsfw-art") || channel.equals("other")) && !(e.getMessage().getAttachments().isEmpty())) {
            e.getMessage().addReaction("albedo1:652666759651917841").queue();
        }
    }

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        if (e.getReactionEmote().getName().equals("albedo1") && !e.getMember().getUser().equals(e.getJDA().getSelfUser())) {
            if (e.getChannel().getName().equals("completed-art") || e.getChannel().getName().equals("work-in-progress") || e.getChannel().getName().equals("nsfw-art") || e.getChannel().getName().equals("other")) {
                MessageChannel m = e.getChannel();
                m.retrieveMessageById(e.getMessageId()).queue(message -> {

                    String user = message.getAuthor().getId();

                    Emote emote = e.getReactionEmote().getEmote();
                    if (user.equals(e.getUserId())) {
                        message.removeReaction(emote, e.getMember().getUser()).queue();
                        return;
                    }

                    String URI = Config.getURI("URI");
                    MongoClient mongoClient = MongoClients.create(URI);
                    MongoDatabase db = mongoClient.getDatabase("Albedo");
                    MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

                    Document found = collection.find(new Document("UserID", user)).first();
                    if (found != null) {
                        int old = (Integer) found.get("Adores");
                        int newValue = old + 1;

                        Document query = new Document();
                        query.append("UserID", user);
                        Document setData = new Document();
                        setData.append("Adores", newValue);
                        Document update = new Document();
                        update.append("$set", setData);
                        collection.updateOne(query, update);
                    }
                });
            }
        }
    }

    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent e) {
        if (e.getReactionEmote().getName().equals("albedo1") && !e.getMember().getUser().equals(e.getJDA().getSelfUser())) {
            if (e.getChannel().getName().equals("completed-art") || e.getChannel().getName().equals("work-in-progress") || e.getChannel().getName().equals("nsfw-art") || e.getChannel().getName().equals("other")) {
                MessageChannel m = e.getChannel();
                m.retrieveMessageById(e.getMessageId()).queue(message -> {

                    String user = message.getAuthor().getId();

                    if (user.equals(e.getUserId())) {
                        return;
                    }

                    String URI = Config.getURI("URI");
                    MongoClient mongoClient = MongoClients.create(URI);
                    MongoDatabase db = mongoClient.getDatabase(Config.getDB("DATABASE"));
                    MongoCollection<Document> collection = db.getCollection(Config.getCol("COLLECTION"));

                    Document found = collection.find(new Document("UserID", user)).first();
                    if (found != null) {
                        int old = (Integer) found.get("Adores");
                        int newValue = old - 1;

                        Document query = new Document();
                        query.append("UserID", user);
                        Document setData = new Document();
                        setData.append("Adores", newValue);
                        Document update = new Document();
                        update.append("$set", setData);
                        collection.updateOne(query, update);
                    }
                });
            }
        }
    }
}
