package me.agramon.albedo.commands.administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

public class Kick extends Command {
    public Kick() {
        super.name = "kick";
        super.help = "Kick a user from the server";
        super.category = new Category("Administration");
    }

    @Override
    protected void execute(CommandEvent e) {
        Member member = e.getMember();
        Member selfMember = e.getGuild().getSelfMember();
        Member target;

        String [] args = e.getArgs().split("\\s+");
        String reason = "";
        if (args.length >= 1) {
            reason = args[1];
        }

        if (e.getMessage().getMentionedMembers().size() != 0){
            target = e.getMessage().getMentionedMembers().get(0);
        } else if (!e.getArgs().isEmpty() && e.getJDA().getUserById(e.getArgs()) != null){
            target = e.getGuild().getMemberById(e.getArgs());
        } else {
            e.reply("Please mention a player you would like to ban!");
            return;
        }

        if (!member.hasPermission(Permission.KICK_MEMBERS) || !member.canInteract(target)) {
            e.reply("Begone! You cannot tell me what to do, scum!");
            return;
        }

        if (!selfMember.hasPermission(Permission.KICK_MEMBERS) || !selfMember.canInteract(target)) {
            e.reply("It is impossible, however I will do as you wish!");
            return;
        }

        e.getGuild().kick(target, reason);

        e.reply("That scum has been removed from existence!");
    }
}
