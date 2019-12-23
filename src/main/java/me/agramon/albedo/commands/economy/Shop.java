package me.agramon.albedo.commands.economy;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Shop extends Command {
    public Shop() {
        super.name = "shop";
        super.help = "The shop of the server";
        super.cooldown = 5;
        super.category = new Category("Economy");
        super.aliases = new String[] {"market", "store"};
    }

    @Override
    protected void execute(CommandEvent e) {
        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setTitle("Argonaut Market")
                .setDescription("Do >buy <role name> to purchase the role!")
                .addField("Roles",
                        "💰 5000 - **Weeb** \n\n" +
                                "💰 5000 - **Baka** \n\n" +
                                "💰 5000 - **Hentai** \n\n" +
                                "💰 7000 - **Trap** \n\n" +
                                "💰 7000 - **Senpai** \n\n" +
                                "💰 10000 - **Lewd** \n\n" +
                                "💰 10000 - **No Bulli** \n\n", false)
                .setImage("https://i.pinimg.com/originals/64/44/3b/64443bc6bebd32b874f482ac7d74eaf6.jpg");

        e.reply(eb.build());
    }
}
