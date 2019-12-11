package me.agramon.albedo.commands.administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

import java.util.List;

public class AddUsers extends Command {
    public AddUsers() {
        super.name = "addusers";
        super.help = "Adds all users to the MongoDB database";
        super.category = new Category("Administration");
        super.hidden = true;
    }

    @Override
    protected void execute(CommandEvent e) {
        if (!e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            e.reply("Begone! You cannot tell me what to do, scum!");
            return;
        }

        List<Member> members = e.getGuild().getMembers();
        int added = 0;
        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Adores");

        for (int i = 0; i < members.size(); i++) {
            String user = members.get(i).getId();
            Document found = collection.find(new Document("UserID", user)).first();
            if (found != null) {
                // Do Nothing
            } else {
                Document document = new Document("UserID", user);
                document.append("Adores", 0);
                collection.insertOne(document);
                added++;
            }
        }
        e.reply(added + "/" + members.size() + " has been added to the database");
    }
}
