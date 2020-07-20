package me.agramon.albedo;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.agramon.albedo.commands.administration.*;
import me.agramon.albedo.commands.art.*;
import me.agramon.albedo.commands.fun.*;
import me.agramon.albedo.commands.info.*;
import me.agramon.albedo.commands.nsfw.*;
import me.agramon.albedo.commands.roleplay.*;
import me.agramon.albedo.events.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class AlbedoMain {
    private AlbedoMain() throws LoginException {
        JDA jda = JDABuilder.createDefault(Config.getToken("TOKEN")).build();

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix(">");
        builder.setOwnerId("651438686197776385");
        builder.setHelpWord("dmhelp");
        builder.setActivity(Activity.watching("Ainz-sama (͡° ͜ʖ ͡°)"));

        builder.addCommand(new Avatar());
        builder.addCommand(new Help());
        builder.addCommand(new Ping());
        builder.addCommand(new Profile());
        builder.addCommand(new ServerInfo());
        builder.addCommand(new Uptime());

        builder.addCommand(new ArtPrompt());

        builder.addCommand(new Albedo());
        builder.addCommand(new Baka());
        builder.addCommand(new Bowsette());
        builder.addCommand(new E926());
        builder.addCommand(new Horns());
        builder.addCommand(new Kemonomimi());
        builder.addCommand(new Kitsune());
        builder.addCommand(new Moescape());
        builder.addCommand(new Neko());
        builder.addCommand(new Safebooru());
        builder.addCommand(new Search());
        builder.addCommand(new Smug());

        builder.addCommand(new Cuddle());
        builder.addCommand(new Kiss());
        builder.addCommand(new Lick());
        builder.addCommand(new Pat());
        builder.addCommand(new Poke());
        builder.addCommand(new Slap());
        builder.addCommand(new Tickle());

        builder.addCommand(new Anal());
        builder.addCommand(new Blowjob());
        builder.addCommand(new Boobs());
        builder.addCommand(new Cum());
        builder.addCommand(new Danbooru());
        builder.addCommand(new E621());
        builder.addCommand(new Feet());
        builder.addCommand(new Femdom());
        builder.addCommand(new Gelbooru());
        builder.addCommand(new Hentai());
        builder.addCommand(new Konochan());
        builder.addCommand(new Lewd());
        builder.addCommand(new Pussy());
        builder.addCommand(new Rule34());
        builder.addCommand(new Solo());
        builder.addCommand(new Trap());
        builder.addCommand(new Yandere());
        builder.addCommand(new Yuri());

        builder.addCommand(new ChatClear());
        builder.addCommand(new GetId());

        CommandClient client = builder.build();

        jda.addEventListener(client);
        jda.addEventListener(new ArtReaction());
        jda.addEventListener(new Log());
    }

    public static void main(String args[]) throws LoginException {
        new AlbedoMain();
    }
}
