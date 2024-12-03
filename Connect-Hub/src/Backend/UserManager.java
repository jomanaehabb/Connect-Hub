/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL-G3
 */
import Backend.User;
import org.json.*;
import java.io.*;
import java.util.*;
import java.security.MessageDigest;

public class UserManager {
    private static final String USERS_FILE = "users.json";

    // Validate login credentials
    public boolean validateLogin(String email, String password) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(hashPassword(password))) {
                return true; // Found matching user
            }
        }
        return false; // No match found
    }

    // Hash password
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Load users from the JSON file using org.json
    private List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try {
            // Read the JSON file
            FileReader reader = new FileReader(USERS_FILE);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();

            // Parse the JSON data
            JSONArray userArray = new JSONArray(sb.toString());
            
            // Convert each JSON object to User
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userJson = userArray.getJSONObject(i);
                User user = new User(
                        userJson.getString("email"),
                        userJson.getString("username"),
                        userJson.getString("password"),
                        userJson.getString("dateOfBirth"),
                        userJson.getString("status")
                );
                users.add(user);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Save new user or updated list to the file using org.json
    public void saveUsers(List<User> users) {
        try {
            JSONArray userArray = new JSONArray();

            // Convert each User object to a JSONObject
            for (User user : users) {
                JSONObject userJson = new JSONObject();
                userJson.put("email", user.getEmail());
                userJson.put("username", user.getUsername());
                userJson.put("password", user.getPassword());
                userJson.put("dateOfBirth", user.getDateOfBirth());
                userJson.put("status", user.getStatus());
                userArray.put(userJson);
            }

            // Write the JSON array to the file
            FileWriter writer = new FileWriter(USERS_FILE);
            writer.write(userArray.toString(4)); // Pretty print with 4 spaces indentation
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Sign up a new user
    public boolean signUp(String email, String username, String password, String dateOfBirth) {
        // Check if the user already exists
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false; // User already exists
            }
        }
        // Create a new user
        User newUser = new User(email, username, hashPassword(password), dateOfBirth, "offline");
        users.add(newUser);
        saveUsers(users);
        return true; // Successfully signed up
    }

    // Update user status (e.g., online/offline)
    public void updateStatus(String email, String status) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setStatus(status);
                saveUsers(users);
                break;
            }
        }
    }
}