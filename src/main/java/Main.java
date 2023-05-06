import task1.*;
import task2.JsonUtilForTask2;
import task3.JsonUtilForTask3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Geo geo = new Geo("-37.3159", "81.1496");
        Address address = new Address("Hostinnaya 15", "Suite 124", "Kyiv", "82492", geo);
        Company company = new Company("Google", "Face to face bifurcated interface", "e-enable strategic applications");
        UserTask1 user1 = new UserTask1(3, "Yan Kurhuzov", "kurhuzov", "yan.dasty@gmail.com", address,"(067) 241-12-51", "yan.com", company);

        System.out.println("SendPost = " + JsonUtilForTask1.sendPost(user1));
        System.out.println();
        System.out.println("SendPut = " + JsonUtilForTask1.sendPut(user1));
        System.out.println();
        System.out.println("SendDelete = " + JsonUtilForTask1.sendDelete(5));
        System.out.println();
        JsonUtilForTask1.getUsers().stream().forEach(System.out::println);
        System.out.println();
        System.out.println("GetUserById(6) = " + JsonUtilForTask1.getUserById(6));
        System.out.println();
        System.out.println("GetUserByUsername(Bret) = " + JsonUtilForTask1.getUserByUsername("Bret"));
        System.out.println();
        JsonUtilForTask2.getLastCommentOfUserById(6);
        System.out.println();
        JsonUtilForTask3.getOpenTasksByUserId(5).stream().forEach(System.out::println);
    }
}
