package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.ImageBoardAPI;

public class Safebooru extends ImageBoardAPI {
    public Safebooru() {
        super("safebooru", "Random SFW anime image", 0, "safebooru");
        super.aliases = new String[] {"sb"};
        super.category = new Category("Fun");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
