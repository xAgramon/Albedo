package me.agramon.albedo;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.agramon.albedo.commands.administration.AddUsers;
import me.agramon.albedo.commands.administration.ChatClear;
import me.agramon.albedo.commands.administration.SetAdores;
import me.agramon.albedo.commands.administration.SetCredits;
import me.agramon.albedo.commands.economy.*;
import me.agramon.albedo.commands.fun.*;
import me.agramon.albedo.commands.info.*;
import me.agramon.albedo.commands.nsfw.*;
import me.agramon.albedo.commands.roleplay.*;
import me.agramon.albedo.events.ArtReaction;
import me.agramon.albedo.events.UserCreateDB;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class AlbedoMain {
    private AlbedoMain() throws LoginException {
        final JDA jda = new JDABuilder(AccountType.BOT)
                .setToken(Config.getKey("TOKEN")).build();

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

        builder.addCommand(new Albedo());
        builder.addCommand(new Bowsette());
        builder.addCommand(new E926());
        builder.addCommand(new Horns());
        builder.addCommand(new Kitsune());
        builder.addCommand(new me.agramon.albedo.commands.fun.Neko());
        builder.addCommand(new Paste());
        builder.addCommand(new Safebooru());
        builder.addCommand(new Search());

        builder.addCommand(new Balance());
        builder.addCommand(new Daily());

        builder.addCommand(new Cuddle());
        builder.addCommand(new Kiss());
        builder.addCommand(new Lick());
        builder.addCommand(new Pat());
        builder.addCommand(new Poke());
        builder.addCommand(new Slap());
        builder.addCommand(new Tickle());

        builder.addCommand(new Danbooru());
        builder.addCommand(new E621());
        builder.addCommand(new Gelbooru());
        builder.addCommand(new Konochan());
        builder.addCommand(new Rule34());
        builder.addCommand(new Yandere());

        builder.addCommand(new AddUsers());
        builder.addCommand(new ChatClear());
        builder.addCommand(new SetAdores());
        builder.addCommand(new SetCredits());

        CommandClient client = builder.build();

        jda.addEventListener(client);
        jda.addEventListener(new ArtReaction());
        jda.addEventListener(new Log());
        jda.addEventListener(new UserCreateDB());
    }

    public static void main(String args[]) throws LoginException {
        long enable = System.currentTimeMillis();
        new AlbedoMain();
    }
}
