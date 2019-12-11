package me.agramon.albedo.commands.administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ChatClear extends Command {
    public ChatClear() {
        super.name = "chatclear";
        super.category = new Category("Administration");
        super.aliases = new String[]{"clear", "purge", "prune"};
        super.help = "Clears chat";
    }

    @Override
    protected void execute(CommandEvent e) {
        String args = e.getArgs();

        TextChannel channel = e.getTextChannel();
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

        if (args == "") {
            e.reply("Please tell me many messages do you want to clear??");
        }

        int amount;
        try {
            amount = Integer.parseInt(args);
        } catch (NumberFormatException ignored) {
            e.reply("Can't you tell the difference between a letter and a number?!");
            return;
        }

        channel.getIterableHistory()
                .takeAsync(amount)
                .thenApplyAsync((messages) -> {
                    List<Message> goodMessages = messages.stream()
                            .filter((m) -> m.getTimeCreated().isBefore(
                                    OffsetDateTime.now().plus(2, ChronoUnit.WEEKS)
                            ))
                            .collect(Collectors.toList());

                    channel.purgeMessages(goodMessages);

                    return goodMessages.size();
                })
                .whenCompleteAsync(
                        (count, thr) -> channel.sendMessageFormat("I have purged `%d` messages!!", count).queue(
                                (message) -> message.delete().queueAfter(3, TimeUnit.SECONDS)
                        )
                )
                .exceptionally((thr) -> {
                    String cause = "";

                    if (thr.getCause() != null) {
                        cause = " caused by: " + thr.getCause().getMessage();
                    }

                    channel.sendMessageFormat("Error: %s%s", thr.getMessage(), cause).queue();

                    return 0;
                });
    }
}
