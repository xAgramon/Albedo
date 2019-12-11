package me.agramon.albedo.commands.fun;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.agramon.albedo.api.ImageBoardAPI;

public class E926 extends ImageBoardAPI {
    public E926() {
        super("e926", "Random SFW furry image", 5, "e926");
        super.category = new Category("Fun");
    }

    @Override
    protected void execute(CommandEvent e) {
        super.execute(e);
    }
}
