package me.agramon.albedo.commands.economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.agramon.albedo.Config;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

public class Buy extends Command {
    public Buy() {
        super.name = "buy";
        super.help = "Buys an item in the shop";
        super.category = new Category("Economy");
        super.cooldown = 5;
    }

    @Override
    protected void execute(CommandEvent e) {
        if (e.getArgs().isEmpty()) {
            e.reply("What would you like to buy in the shop?");
            return;
        }

        Role weeb = e.getGuild().getRoleById("658509225886089235");
        Role baka = e.getGuild().getRoleById("658509229937786881");
        Role hentai = e.getGuild().getRoleById("658509231313649674");
        Role trap = e.getGuild().getRoleById("658509232756359198");
        Role senpai = e.getGuild().getRoleById("658509898815897620");
        Role lewd = e.getGuild().getRoleById("658509699964076032");
        Role nobulli = e.getGuild().getRoleById("658203795259654145");

        String URI = Config.getURI("URI");
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase db = mongoClient.getDatabase("Albedo");
        MongoCollection<Document> collection = db.getCollection("Anime Argonauts");

        User user = e.getMember().getUser();
        Document found = collection.find(new Document("UserID", user.getId())).first();

        int oldCredits = (Integer) (found.get("Credits"));

        if (e.getArgs().equalsIgnoreCase("Weeb")) {
            if (e.getMember().getRoles().contains(weeb)) {
                e.reply("You already have that role you fool!");
                return;
            }
            if (oldCredits >= 5000) {
                e.getGuild().addRoleToMember(user.getId(), weeb).queue();
                updateCredits(collection, user, oldCredits, 5000);
                e.reply("You have bought the role!");
            } else {
                e.reply("What a poor scum! You cannot afford that role!");
            }
        } else if (e.getArgs().equalsIgnoreCase("Baka")) {
            if (e.getMember().getRoles().contains(baka)) {
                e.reply("You already have that role you fool!");
                return;
            }
            if (oldCredits >= 5000) {
                e.getGuild().addRoleToMember(user.getId(), baka).queue();
                updateCredits(collection, user, oldCredits, 5000);
                e.reply("You have bought the role!");
            } else {
                e.reply("What a poor scum! You cannot afford that role!");
            }
        } else if (e.getArgs().equalsIgnoreCase("Hentai")) {
            if (e.getMember().getRoles().contains(hentai)) {
                e.reply("You already have that role you fool!");
                return;
            }
            if (oldCredits >= 5000) {
                e.getGuild().addRoleToMember(user.getId(), hentai).queue();
                updateCredits(collection, user, oldCredits, 5000);
                e.reply("You have bought the role!");
            } else {
                e.reply("What a poor scum! You cannot afford that role!");
            }
        } else if (e.getArgs().equalsIgnoreCase("Trap")) {
            if (e.getMember().getRoles().contains(trap)) {
                e.reply("You already have that role you fool!");
                return;
            }
            if (oldCredits >= 7000) {
                e.getGuild().addRoleToMember(user.getId(), trap).queue();
                updateCredits(collection, user, oldCredits, 7000);
                e.reply("You have bought the role!");
            } else {
                e.reply("What a poor scum! You cannot afford that role!");
            }
        } else if (e.getArgs().equalsIgnoreCase("Senpai")) {
            if (e.getMember().getRoles().contains(senpai)) {
                e.reply("You already have that role you fool!");
                return;
            }
            if (oldCredits >= 7000) {
                e.getGuild().addRoleToMember(user.getId(), senpai).queue();
                updateCredits(collection, user, oldCredits, 7000);
                e.reply("You have bought the role!");
            } else {
                e.reply("What a poor scum! You cannot afford that role!");
            }
        } else if (e.getArgs().equalsIgnoreCase("Lewd")) {
            if (e.getMember().getRoles().contains(lewd)) {
                e.reply("You already have that role you fool!");
                return;
            }
            if (oldCredits >= 10000) {
                e.getGuild().addRoleToMember(user.getId(), lewd).queue();
                updateCredits(collection, user, oldCredits, 10000);
                e.reply("You have bought the role!");
            } else {
                e.reply("What a poor scum! You cannot afford that role!");
            }
        } else if (e.getArgs().equalsIgnoreCase("No Bulli") || e.getArgs().equalsIgnoreCase("NoBulli")) {
            if (e.getMember().getRoles().contains(nobulli)) {
                e.reply("You already have that role you fool!");
                return;
            }
            if (oldCredits >= 10000) {
                e.getGuild().addRoleToMember(user.getId(), nobulli).queue();
                updateCredits(collection, user, oldCredits, 10000);
                e.reply("You have bought the role!");
            } else {
                e.reply("What a poor scum! You cannot afford that role!");
            }
        }
    }

    public void updateCredits(MongoCollection<Document> collection, User user, int oldCredits, int price) {
        Document query = new Document();
        query.append("UserID", user.getId());

        Document setData = new Document();
        setData.append("Credits", oldCredits - price);

        Document update = new Document();
        update.append("$set", setData);
        collection.updateOne(query, update);
    }
}
