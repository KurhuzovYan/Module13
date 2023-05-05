package task2;

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

public class JsonUtilForComments {
    private static final String USER_URL = "https://jsonplaceholder.typicode.com/users";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static void getLastCommendAndRecordInFile(int user) throws IOException, InterruptedException {

        HttpResponse<String> postResponse = getHttpResponse(USER_URL, "/" + user + "/posts");
        Type typeForUser = TypeToken.getParameterized(List.class, UserTask2.class).getType();

        List<UserTask2> posts = getList(postResponse, typeForUser);
        int lastPostId = posts.stream()
                .mapToInt(i -> i.getId())
                .max()
                .getAsInt();

        HttpResponse<String> commentResponse = getHttpResponse("https://jsonplaceholder.typicode.com/posts/", lastPostId + "/comments");

        Type typeForComment = TypeToken.getParameterized(List.class, Comment.class).getType();
        List<Comment> comments = getList(commentResponse, typeForComment);

        try (FileWriter mfr = new FileWriter("user-" + user + "-post-" + lastPostId + "-comments.json")) {
            String res = GSON.toJson(comments);
            mfr.write(res);

        } catch (IOException e) {
            throw new IOException();
        }
    }

    private static<T> List<T> getList(HttpResponse<String> response, Type type) {
        List<T> currentUser = GSON.fromJson(response.body(), type);
        return currentUser;
    }

    private static HttpResponse<String> getHttpResponse(String url, String parameter) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + parameter))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
