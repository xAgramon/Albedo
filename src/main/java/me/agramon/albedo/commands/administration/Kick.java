package me.agramon.albedo.commands.administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class Kick extends Command {
    public Kick() {
        super.name = "kick";
        super.help = "Kicks a user from the server";
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

        if (temp.isEmpty()) {
            e.reply("The correct usage is >kick <user> (reason)");
            return;
        }

        final Member target = e.getMessage().getMentionedMembers().get(0);

        if (e.getSelfMember().equals(target)) {
            e.reply("Noo! Please don't kick me :C");
            return;
        }

        if (e.getMember().equals(target)) {
            e.reply("You cannot kick yourself!");
            return;
        }

        if (!e.getSelfMember().canInteract(target) || !e.getSelfMember().hasPermission(Permission.KICK_MEMBERS)) {
            e.reply("I do not have permission to kick that user!");
            return;
        }

        if (!e.getMember().canInteract(target) || !e.getSelfMember().hasPermission(Permission.KICK_MEMBERS)) {
            e.reply("I do not have permission to kick that user!");
            return;
        }

        if (args.length < 2) {
            e.getGuild()
                    .kick(target)
                    .queue();
        } else {
            e.getGuild()
                    .kick(target, args[1])
                    .reason(args[1])
                    .queue();
        }

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setDescription(target.getUser().getName() + " has been kicked from the server!");
        e.reply(eb.build());
    }
}
