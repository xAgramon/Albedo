package me.agramon.albedo.commands.economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

public class Balance extends Command {
    public Balance() {
        super.name = "balance";
        super.help = "Shows amount of credits a user has";
        super.cooldown = 3;
        super.aliases = new String[]{"bal"};
        super.category = new Category("Economy");
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

        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        Document found = collection.find(new Document("UserID", name.getUser().getId())).first();

        int credits = (Integer) found.get("Credits");

        e.reply("```" + name.getUser().getName() + " has " + credits + " credits!```");
    }
}
