package me.agramon.albedo.commands.administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.Permission;
import org.bson.Document;

public class SetAdores extends Command {
    public SetAdores() {
        super.name = "setadores";
        super.help = "Sets a users adores!";
        super.category = new Category("Administration");
    }

    @Override
    protected void execute(CommandEvent e) {
        if (!e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            e.reply("Begone! You cannot tell me what to do, scum!");
            return;
        }

        String temp = e.getArgs();
        String [] args = temp.split("\\s+");


        if (temp.isEmpty() || args.length != 2) {
            e.reply("Invalid arguments! The correct usage is >setadores <user> <# of adores>");
            return;
        }

        String user = args[0];
        int adores = Integer.parseInt(args[1]);

        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        Document found = collection.find(new Document("UserID", user)).first();
        if (found != null) {
            Document query = new Document();
            query.append("UserID", user);
            Document setData = new Document();
            setData.append("Adores", adores);
            Document update = new Document();
            update.append("$set", setData);
            collection.updateOne(query, update);
            e.reply(e.getJDA().getUserById(user).getName() + " now has " + args[1] + " credits!");
        } else {
            e.reply("Error! " + e.getJDA().getUserById(user).getName() + " is not in the database!");
        }
    }
}
