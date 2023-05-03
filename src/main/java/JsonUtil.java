import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final String USER_URL = "https://jsonplaceholder.typicode.com/users";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static void sendPost(User user) throws IOException, InterruptedException {

        final String body = GSON.toJson(user);

        try (FileWriter mfw = new FileWriter("my.json")) {
            mfw.write(body);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("my.json")))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static void sendPut(User user) throws IOException, InterruptedException {

        final String body = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void sendDelete(User user) throws IOException, InterruptedException {
        final String body = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL))
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static List<User> getUsers() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        Type type = TypeToken.getParameterized(List.class, User.class).getType();
        List<User> users = new Gson().fromJson(response.body(), type);

        return users;
    }
}
