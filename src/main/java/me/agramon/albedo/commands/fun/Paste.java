package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.menudocs.paste.PasteClient;
import org.menudocs.paste.PasteClientBuilder;

public class Paste extends Command {
    public Paste() {
        super.name = "paste";
        super.category = new Category("Fun");
        super.help = "Creates a 10 minute pastebin";
        super.cooldown = 10;
        super.arguments = "(message)";
    }

    private final PasteClient client = new PasteClientBuilder()
            .setUserAgent("Albedo")
            .setDefaultExpiry("10m")
            .build();

    @Override
    protected void execute(CommandEvent e) {
        final String args = e.getArgs();
        final TextChannel channel = e.getTextChannel();

        if (args == "") {
            channel.sendMessage("Missing message!").queue();
            return;
        }

        client.createPaste("", args).async(
                (id) -> client.getPaste(id).async((paste) -> {
                    EmbedBuilder builder = new EmbedBuilder()
                            .setTitle("Paste " + id, paste.getPasteUrl())
                            .setDescription("```")
                            .appendDescription(paste.getLanguage().getId())
                            .appendDescription("\n")
                            .appendDescription(paste.getBody())
                            .appendDescription("```");

                    channel.sendMessage(builder.build()).queue();
                })
        );
    }
}
