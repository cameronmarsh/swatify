package edu.swarthmore.cs.cs71.swatify.util;

import org.json.JSONObject;

import java.io.IOException;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Secrets {
    private static String spotifyClientId;
    private static String spotifyClientSecret;

    private static void readJsonFromFile() {
        try {
            JSONObject jsonObj = new JSONObject(new String(readAllBytes(get("src/main/resources/secrets.json"))));

            spotifyClientId = jsonObj.getString("spotifyClientId");
            spotifyClientSecret = jsonObj.getString("spotifyClientSecret");
        } catch (IOException e) {
            System.out.println("Could not find the secrets file");
            e.printStackTrace();
        }
    }

    public static String getSpotifyClientId() {
        if (spotifyClientId == null) {
            readJsonFromFile();
        }

        return spotifyClientId;
    }

    public static String getSpotifyClientSecret() {
        if (spotifyClientSecret == null) {
            readJsonFromFile();
        }

        return spotifyClientSecret;
    }
}
