package task1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.List;

public class JsonUtilForUsers {
    private static final String USER_URL = "https://jsonplaceholder.typicode.com/users";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static void sendPost(UserTask1 user) throws IOException, InterruptedException {


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

    public static void sendPut(UserTask1 user) throws IOException, InterruptedException {

        final String body = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/3"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void sendDelete() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/3"))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static List<UserTask1> getUsers() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        Type type = TypeToken.getParameterized(List.class, UserTask1.class).getType();
        List<UserTask1> users = new Gson().fromJson(response.body(), type);

        try (FileWriter mfr = new FileWriter("allUsers.json")) {
            String allUsers = GSON.toJson(users);
            mfr.write(allUsers);
        }

        return users;
    }

    public static UserTask1 getUserById(int id) throws IOException, InterruptedException {
        UserTask1 currentUser = getUsers().get(id);
        return currentUser;
    }

    public static UserTask1 getUserByUsername(String username) throws IOException, InterruptedException {
        UserTask1 currentUser = null;

        List<UserTask1> users = getUsers();
        for (UserTask1 user : users) {
            if (user.getUsername().equals(username)) {
                currentUser = user;
            }
        }
        return currentUser;
    }




}
