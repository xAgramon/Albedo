package me.agramon.albedo.commands.administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;

public class GetId extends Command {
    public GetId() {
        super.name = "getid";
        super.category = new Category("Administration");
        super.help = "Gets the ID of a mentioned member, channel, emote, or role";
    }
    @Override
    protected void execute(CommandEvent e) {
        Member member = e.getMember();
        Member selfMember = e.getSelfMember();

        if (!member.hasPermission(Permission.MESSAGE_MANAGE)) {
            e.reply("Begone! You cannot tell me what to do, scum!");
            return;
        }
        if (!selfMember.hasPermission(Permission.MESSAGE_MANAGE)) {
            e.reply("It is impossible, however I will do as you wish!");
            return;
        }
        if (e.getArgs().isEmpty()) {
            e.reply("Please tell me what role you wish for me to add!");
        }

        List<Member> members = e.getMessage().getMentionedMembers();
        List<Role> roles = e.getMessage().getMentionedRoles();
        List<TextChannel> channels = e.getMessage().getMentionedChannels();

        EmbedBuilder eb = new EmbedBuilder();
        StringBuilder sb = new StringBuilder();
        for (Member m : members) {
            sb.append(m.getNickname() + " = " + m.getId());
            sb.append("\n");
        }
        for (Role r : roles) {
            sb.append(r.getName() + " = " + r.getId());
            sb.append("\n");
        }
        for (TextChannel t : channels) {
            sb.append(t.getName() + " = " + t.getId());
            sb.append("\n");
        }
        eb.setColor(Color.MAGENTA);
        eb.setDescription(sb.toString());
        e.reply(eb.build());
    }
}
