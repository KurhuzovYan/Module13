import task1.*;
import task2.JsonUtilForComments;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Geo geo = new Geo("-37.3159", "81.1496");
        Address address = new Address("Hostinnaya 15", "Suite 124", "Kyiv", "82492", geo);
        Company company = new Company("Google", "Face to face bifurcated interface", "e-enable strategic applications");
        UserTask1 user1 = new UserTask1(1, "Yan Kurhuzov", "kurhuzov", "yan.dasty@gmail.com", address,"(067) 241-12-51", "yan.com", company);


//
//        Task1.User userById = Task1.JsonUtil.getUserById(2);
//        userById.setName("Bauch");
//        Task1.JsonUtil.sendPut(userById);
//        Task1.JsonUtil.sendDelete();
//        Task1.JsonUtil.getUsers();


//       Task1.JsonUtil.sendPost(user1);
//       Task1.JsonUtil.sendPut(user1);
//       Task1.JsonUtil.sendDelete(user1);
//        List<Task1.User> users = Task1.JsonUtil.getUsers();
//        Task1.User user2 = users.get(1);
//        Task1.JsonUtil.sendDelete(user2);
//        System.out.println("Task1.JsonUtil.getInformationOfCurrentUser = " + Task1.JsonUtil.getUserById(0));
//        System.out.println("Task1.JsonUtil.getUserByUsername(\"Bret\") = " + Task1.JsonUtil.getUserByUsername("sada"));
        JsonUtilForComments.getLastCommendAndRecordInFile(4);
    }

}
