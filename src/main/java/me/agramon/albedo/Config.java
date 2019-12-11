package me.agramon.albedo;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.configure().directory("./").load();

    public static String getKey(String key) {
        return dotenv.get(key);
    }

    public static String getURI(String uri) {
        return dotenv.get(uri);
    }
}
