package me.agramon.albedo;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.configure().directory("./").load();

    public static String getToken(String token) {
        return dotenv.get(token);
    }
}
