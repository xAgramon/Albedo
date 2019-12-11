package me.agramon.albedo.commands.roleplay;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.PurrBotAPI;

public class Pat extends PurrBotAPI {
    public Pat() {
        super("pat", "Pat someone", 5, "/sfw/pat/gif", "pats");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
