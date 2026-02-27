package AddUser;



public class UserGenerator {

    public static String generateUsername() {
        return "user" + System.currentTimeMillis();
    }
}
