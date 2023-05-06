package task1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.http.HttpClient;

public class Constants {

    public static final String USER_URL = "https://jsonplaceholder.typicode.com/users";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
}
