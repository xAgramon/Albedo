package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.ImageBoardAPI;

public class Bowsette extends ImageBoardAPI {
    public Bowsette() {
        super("bowsette", "Random Bowsette image", 5, "safebooru", "bowsette");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
