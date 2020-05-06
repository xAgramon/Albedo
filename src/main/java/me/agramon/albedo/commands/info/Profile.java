package me.agramon.albedo.commands.info;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class Profile extends Command {
    public Profile() {
        super.name = "profile";
        super.help = "Shows info about yourself";
        super.category = new Category("Help/Info");
        super.cooldown = 3;
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

        Emote emote = e.getJDA().getEmoteById("652666759651917841");

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setThumbnail(user.getAvatarUrl())
                .setAuthor("Profile of " + user.getName(), null, user.getAvatarUrl())
                .addField("Username:", user.getName(), false)
                .addField("Account Created:", user.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Server Joined:", e.getGuild().getMember(user).getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false);

        e.reply(eb.build());
    }
}
