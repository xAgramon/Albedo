package me.agramon.albedo;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.configure().directory("./").load();

    public static String getToken(String token) {
        return dotenv.get(token);
    }

    public static String getURI(String uri) {
        return dotenv.get(uri);
    }

    public static String getDB(String database) { return dotenv.get(database); }

    public static String getCol(String collection) { return dotenv.get(collection); }
}
