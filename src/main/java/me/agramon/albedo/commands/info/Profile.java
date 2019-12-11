package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class Profile extends Command {
    public Profile() {
        super.name = "profile";
        super.help = "You're conceited";
        super.category = new Category("Help/Info");
        super.cooldown = 5;
        super.arguments = "<name>";
    }

    @Override
    protected void execute(CommandEvent e) {
        Member name;

        if (e.getArgs() == "") {
            name = e.getMember();
        } else {
            name = e.getMessage().getMentionedMembers().get(0);
        }

        int adores = 0;
        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Adores");

        Document found = collection.find(new Document("UserID", e.getMember().getId())).first();
        if (found != null) {
            adores = (Integer) found.get("Adores");

        } else {
            Document document = new Document("UserID", e.getMember().getId());
            document.append("Adores", 0);
            collection.insertOne(document);
        }

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setThumbnail(name.getUser().getAvatarUrl())
                .setAuthor("Profile of " + name.getUser().getName(), null, name.getUser().getAvatarUrl())
                .addField("Username:", name.getUser().getName(), false)
                //.addField("Nickname:", name.getNickname() == null ? name.getUser().getName() : name.getNickname(), false)
                .addField("Account Created:", name.getUser().getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Server Joined:", name.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Adores :heart::", adores + "", false);

                /*
                .addField("Roles:", getRoles(name), false)
                .addField("Status:", name.getOnlineStatus().toString(), false)
                .addField("Bot Account", name.getUser().isBot() ? "Yes" : "No", false);
                */

        e.reply(eb.build());
    }

    private String getRoles (Member name) {
        String roles = "";
        for (int i = 0; i < name.getRoles().size(); i++) {
            String temp = name.getRoles().get(i) + "";
            if (roles != "") {
                roles += ", ";
            }
            roles += temp.substring(2, temp.indexOf("("));
        }
        return roles;
    }
}
