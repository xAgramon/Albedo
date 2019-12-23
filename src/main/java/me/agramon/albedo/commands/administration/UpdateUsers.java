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
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

import java.util.List;

public class UpdateUsers extends Command {
    public UpdateUsers() {
        super.name = "updateusers";
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
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        for (int i = 0; i < members.size(); i++) {
            // Add Users
            String user = members.get(i).getId();
            Document found = collection.find(new Document("UserID", user)).first();
            if (found == null) {
                Document document = new Document("UserID", user);
                document.append("Adores", 0);
                document.append("Credits", 0);
                collection.insertOne(document);
                added++;
            } else {
                boolean toUpdate = false;

                Document query = new Document();
                query.append("UserID", found.get("UserID"));

                Document setData = new Document();
                if (found.get("Adores") == null) {
                    setData.append("Adores", 0);
                    toUpdate = true;
                }
                if (found.get("Credits") == null) {
                    setData.append("Credits", 0);
                    toUpdate = true;
                }
                if (toUpdate) {
                    Document update = new Document();
                    update.append("$set", setData);
                    collection.updateOne(query, update);
                    added++;
                }
            }

            // Remove Users
            User removeUser = e.getJDA().getUserById(found.get("UserID").toString());
            if (e.getGuild().getMember(removeUser) == null) {
                collection.deleteOne(found);
            }
        }
        e.reply(added + "/" + members.size() + " users has been added to the database");
    }
}
