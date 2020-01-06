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
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class Profile extends Command {
    public Profile() {
        super.name = "profile";
        super.help = "Shows info about yourself";
        super.category = new Category("Help/Info");
        super.cooldown = 5;
        super.arguments = "<name>";
    }

    @Override
    protected void execute(CommandEvent e) {
        User user;

        if (e.getArgs().isEmpty()) {
            user = e.getMember().getUser();
        } else if (e.getMessage().getMentionedMembers().size() != 0){
            user = e.getMessage().getMentionedMembers().get(0).getUser();
        } else if (e.getJDA().getUserById(e.getArgs()) != null) {
            user = e.getJDA().getUserById(e.getArgs());
        } else {
            e.reply("The currect usage is >profile @user");
            return;
        }

        String adores;
        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        Document found = collection.find(new Document("UserID", user.getId())).first();
        if (found != null) {
            adores = found.get("Adores") + "";
        } else {
            e.reply("You are current not in the database. Please contact an administrator!");
            return;
        }

        Emote emote = e.getJDA().getEmoteById("652666759651917841");

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setThumbnail(user.getAvatarUrl())
                .setAuthor("Profile of " + user.getName(), null, user.getAvatarUrl())
                .addField("Username:", user.getName(), false)
                .addField("Account Created:", user.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Server Joined:", e.getGuild().getMember(user).getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Adores " + emote.getAsMention() + ":", adores, false);

        e.reply(eb.build());
    }
}
