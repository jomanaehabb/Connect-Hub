/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL-G3
 */

class UserDatabase {
    private static final String DATABASE_FILE = "users.json";
    private Map<String, User> users = new HashMap<>();

    public UserDatabase() {
        loadDatabase();
    }

    public void saveUser(User user) {
        users.put(user.getEmail(), user);
        saveDatabase();
    }

    public User getUser(String email) {
        return users.get(email);
    }

    private void loadDatabase() {
        try {
            if (!Files.exists(Paths.get(DATABASE_FILE))) return;
            String json = Files.readString(Paths.get(DATABASE_FILE));
            users = new Gson().fromJson(json, new TypeToken<Map<String, User>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Failed to load user database.");
        }
    }

    private void saveDatabase() {
        try {
            String json = new Gson().toJson(users);
            Files.writeString(Paths.get(DATABASE_FILE), json);
        } catch (IOException e) {
            System.out.println("Failed to save user database.");
        }
    }
}
