package Model;

public class UserValidator {
    public static boolean confirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
