package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.ImageBoardAPI;

public class Horns extends ImageBoardAPI {
    public Horns() { super ("horns", "Random image of horned anime girls <3", 3,"safebooru","demon_horns"); }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}