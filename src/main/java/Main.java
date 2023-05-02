import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Geo geo1 = new Geo("-37.3159", "81.1496");
        Address address1 = new Address("Hostinnaya 15", "Suite 124", "Kyiv", "82492", geo1);
        Company company1 = new Company("Google", "Face to face bifurcated interface", "e-enable strategic applications");
        User user1 = new User(1, "Yan Kurhuzov", "kurhuzov", "yan.dasty@gmail.com", address1,"(067) 241-12-51", "yan.com", company1);

//       JsonUtil.sendPost(user1);
       JsonUtil.sendPut(user1);
    }
}
