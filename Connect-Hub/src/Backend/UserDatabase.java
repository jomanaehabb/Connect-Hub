/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author DELL-G3
 */

import java.io.*;
import java.util.*;

public class UserDatabase {
    private static final String FILE_NAME = "users.txt";

    // Save user to the file
    public static void saveUser(User user) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(user.toString());
        writer.newLine();
        writer.close();
    }

    // Get user by email (used for login)
    public static User getUserByEmail(String email) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = reader.readLine()) != null) {
            User user = User.fromString(line);
            if (user.getEmail().equals(email)) {
                reader.close();
                return user;
            }
        }
        reader.close();
        return null;
    }

    // Get all users
    public static List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = reader.readLine()) != null) {
            users.add(User.fromString(line));
        }
        reader.close();
        return users;
    }

    // Update user status
    public static void updateUserStatus(String email, String status) throws IOException {
        List<User> users = getAllUsers();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (User user : users) {
            if (user.getEmail().equals(email)) {
            }
            writer.write(user.toString());
            writer.newLine();
        }
        writer.close();
    }

    // Update user profile photo
    public static void updateProfilePhoto(String email, String path) throws IOException {
        List<User> users = getAllUsers();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.updateProfilePhoto(path);
            }
            writer.write(user.toString());
            writer.newLine();
        }
        writer.close();
    }

    // Update cover photo
    public static void updateCoverPhoto(String email, String path) throws IOException {
        List<User> users = getAllUsers();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.updateCoverPhoto(path);
            }
            writer.write(user.toString());
            writer.newLine();
        }
        writer.close();
    }

    // Update bio
    public static void updateBio(String email, String bio) throws IOException {
        List<User> users = getAllUsers();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.updateBio(bio);
            }
            writer.write(user.toString());
            writer.newLine();
        }
        writer.close();
    }

    // Update password
    public static void updatePassword(String email, String newPassword) throws IOException {
        List<User> users = getAllUsers();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.updatePassword(newPassword);
            }
            writer.write(user.toString());
            writer.newLine();
        }
        writer.close();
    }

    // Get the user's posts
    public static List<String> getUserPosts(String email) throws IOException {
        User user = getUserByEmail(email);
        return user != null ? user.getPosts() : new ArrayList<>();
    }

    // Get the user's friends
    public static List<String> getUserFriends(String email) throws IOException {
        User user = getUserByEmail(email);
        return user != null ? user.getFriends() : new ArrayList<>();
    }
}