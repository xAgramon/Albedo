package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class Profile extends Command {
    public Profile() {
        super.name = "profile";
        super.help = "Info about yourself ;)";
        super.category = new Category("Help/Info");
        super.cooldown = 5;
        super.arguments = "<name>";
    }

    @Override
    protected void execute(CommandEvent e) {
        Member name;

        if (e.getArgs().isEmpty()) {
            name = e.getMember();
        } else if (e.getMessage().getMentionedMembers().size() != 0){
            name = e.getMessage().getMentionedMembers().get(0);
        } else {
            e.reply("Invalid arguments! The command is >profile <@user>");
            return;
        }

        String adores;
        String credits;
        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        Document found = collection.find(new Document("UserID", name.getId())).first();
        if (found != null) {
            adores = found.get("Adores") + "";
            credits = found.get("Credits") + "";
        } else {
            e.reply("You are current not in the database. Please contact an administrator");
            return;
        }

        Emote emote = e.getJDA().getEmoteById("652666759651917841");

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setThumbnail(name.getUser().getAvatarUrl())
                .setAuthor("Profile of " + name.getUser().getName(), null, name.getUser().getAvatarUrl())
                .addField("Username:", name.getUser().getName(), false)
                .addField("Account Created:", name.getUser().getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Server Joined:", name.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Adores " + emote.getAsMention() + ":", adores, false)
                .addField("Credits 💰:", credits, false);

        e.reply(eb.build());
    }
}
