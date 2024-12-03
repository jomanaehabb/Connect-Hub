package Backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.UUID;

public class UserAccountManager extends Manager {

    /*public boolean signUp(String email, String username, String password, LocalDate dateOfBirth) {
    if (users.containsKey(email)) return false;
    String userId = generateUniqueUserId();
    User user = new User(userId, email, username, hashPassword(password), dateOfBirth);
    users.put(email, user);
    saveUsers();
    return true;
    }
    public String generateUniqueUserId(){
        String userId;
        do{
            userId = UUID.randomUUID().toString();
        } while (isUserIdTaken(userId));
        return userId;
    }
    public boolean isUserIdTaken(String userId){
        return users.values().stream().anyMatch(user -> user.getUserId().equals(userId));
    }



    public boolean login(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(hashPassword(password))) {
            user.setStatus("Online");
            saveUsers();
            return true;
        }
        return false;
    }

    public void logout(String email) {
        User user = users.get(email);
        if (user != null) {
            user.setStatus("Offline");
            saveUsers();
        }
    }
*/
    private final UserDatabase database = new UserDatabase();

    public boolean signup(String email, String username, String password, String dateOfBirth) {
        if (!validateEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }

        String hashedPassword = hashPassword(password);
        String userId = UUID.randomUUID().toString();
        User newUser = new User(userId, email, username, hashedPassword, dateOfBirth);
        database.saveUser(newUser);
        System.out.println("User signed up successfully!");
        return true;
    }

    public boolean login(String email, String password) {
        User user = database.getUser(email);
        if (user != null && user.isPasswordCorrect(hashPassword(password))) {
            user.setStatus("online");
            database.saveUser(user);
            System.out.println("Login successful.");
            return true;
        }
        System.out.println("Invalid credentials.");
        return false;
    }

    public void logout(String email) {
        User user = database.getUser(email);
        if (user != null) {
            user.setStatus("offline");
            database.saveUser(user);
            System.out.println("Logout successful.");
        }
    }

    private boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found.", e);
        }
    }
}

