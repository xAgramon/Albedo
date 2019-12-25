package me.agramon.albedo.commands.administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

import java.awt.*;
import java.util.List;

public class CreditsAll extends Command {
    public CreditsAll() {
        super.name = "creditsall";
        super.help = "Adds or subtracts credits to every person on the server";
        super.category = new Category("Administration");
    }

    @Override
    protected void execute(CommandEvent e) {
        if (!e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            e.reply("Begone! You cannot tell me what to do, scum!");
            return;
        }

        if (e.getArgs().isEmpty()) {
            e.reply("Invalid arguments! The correct usage is >addcredits <# of credits>");
            return;
        }

        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        List<Member> members = e.getGuild().getMembers();

        for (int i = 0; i < members.size(); i++) {
            String user = members.get(i).getId();

            Document found = collection.find(new Document("UserID", user)).first();

            // Rounds credits to nearest 100
            int oldC = (Integer)(found.get("Credits"));
            int newC = Integer.parseInt(e.getArgs());
            int credits = ((oldC + newC) / 100) * 100;

            if (!members.get(i).getUser().isBot()) {
                Document query = new Document();
                query.append("UserID", found.get("UserID"));

                Document setData = new Document();
                setData.append("Credits", credits);

                Document update = new Document();
                update.append("$set", setData);
                collection.updateOne(query, update);
            }
        }

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setDescription(e.getArgs() +  " credits have been added to all users!");
        e.reply(eb.build());
    }
}
