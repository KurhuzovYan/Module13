package task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtilForTask3 {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static List<UserTask3> getOpenTasksByUserId(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/todos"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        Type type = TypeToken.getParameterized(List.class, UserTask3.class).getType();
        List<UserTask3> allUsers = GSON.fromJson(response.body(), type);

        List<UserTask3> onlyOpenTasks = allUsers.stream()
                .filter(user -> user.getCompleted() == false)
                .collect(Collectors.toList());

        try (FileWriter fr = new FileWriter("open-tasks-for-user-" + userId + ".json")) {
            String openTasks = GSON.toJson(onlyOpenTasks);
            fr.write(openTasks);
        }

        return onlyOpenTasks;
    }
}
