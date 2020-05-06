package me.agramon.albedo.events;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class ArtReaction extends ListenerAdapter {

    private ArrayList<String> artChannel = new ArrayList<String>();

    // Art channels which the bot adds an emote to the artwork (Uses channel ID)
    public ArtReaction() {
        artChannel.add("677598123006361610"); // Astonishing Art
        artChannel.add("703779024639361134"); // Edits
        artChannel.add("649754421126889482"); // Completed Art
        artChannel.add("649754444149293076"); // Work in Progress
        artChannel.add("656687102918000683"); // NSFW Art
        artChannel.add("677600085592506370"); // NSFW WIP
        artChannel.add("662389450231513088"); // Monthly Submissions
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()) { return; }

        String channel = e.getMessage().getChannel().getId();

        // Checks to see if the message is in an art channel
        if (artChannel.contains(channel) && (!e.getMessage().getAttachments().isEmpty() || e.getMessage().getContentRaw().contains("twitter"))) {
            e.getMessage().addReaction("albedo1:652666759651917841").queue();
        }
    }

    // Checks to see if an artist adored their own artwork
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        String channel = e.getChannel().getId();

        if (artChannel.contains(channel) && e.getReactionEmote().getId().equals("652666759651917841")) {
            MessageChannel m = e.getChannel();
            m.retrieveMessageById(e.getMessageId()).queue(message -> {

                Emote emote = e.getReactionEmote().getEmote();
                if (message.getAuthor().equals(e.getUser())) {
                    message.removeReaction(emote, e.getMember().getUser()).queue();
                    return;
                }
            });
        }
    }
}
