package me.agramon.albedo.commands.fun;

import me.agramon.albedo.api.ImageBoardAPI;

public class Moescape extends ImageBoardAPI {
    public Moescape() {
        super("moescape", "Random image of anime in real life", 5, "konachan", "landscape");
        super.category = new Category("Fun");
    }
}
