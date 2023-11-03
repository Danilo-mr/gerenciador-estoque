package Model;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticateUser(String email, String senha) {
        User user = userRepository.getUserByEmail(email);

        if (user != null && user.getSenha().equals(senha)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean registerNewUser(User user) {
        return userRepository.setNewUser(user);
    }

    public boolean isEmailAlreadyRegistered(String email) {
        if (userRepository.getUserByEmail(email) == null) {
            return false;
        } else {
            return true;
        }

    }
}
