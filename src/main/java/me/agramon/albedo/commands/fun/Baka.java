package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.NekosLifeAPI;

public class Baka extends NekosLifeAPI {
    public Baka() {
        super("baka", "You baka!!~", 5, "baka");
        super.category = new Category("Fun");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
