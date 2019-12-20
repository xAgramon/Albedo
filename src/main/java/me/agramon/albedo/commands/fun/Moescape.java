package me.agramon.albedo.commands.fun;

import me.agramon.albedo.api.ImageBoardAPI;

public class Moescape extends ImageBoardAPI {
    public Moescape() {
        super("moescape", "Anime characters in their environment", 5, "konachan", "landscape");
        super.category = new Category("Fun");
    }
}
