package Backend;

import java.time.LocalDate;
import java.util.UUID;

public class UserAccountManager extends Manager {

    public boolean signUp(String email, String username, String password, LocalDate dateOfBirth) {
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
}

