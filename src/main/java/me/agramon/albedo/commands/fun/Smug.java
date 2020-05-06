package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.NekosLifeAPI;

public class Smug extends NekosLifeAPI {
    public Smug() {
        super("smug", "Random Smug GIF", 3, "smug");
        super.category = new Category("Fun");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
