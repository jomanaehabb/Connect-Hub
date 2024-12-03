/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author DELL-G3
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String userId;
    private String email;
    private String username;
    private String password; // Hashed password
    private String dateOfBirth;
    private String status; // Online or offline
    private String profilePhoto; // Path to profile photo
    private String coverPhoto; // Path to cover photo
    private String bio; // User bio
    private List<String> posts; // List of post IDs (or post content for simplicity)
    private List<String> friends; // List of friends (friend email or userId)

    // Constructor for User object
    public User(String email, String username, String password, String dateOfBirth) {
        this.userId = UUID.randomUUID().toString(); // Generate a unique userId
        this.email = email;
        this.username = username;
        this.password = hashPassword(password); // Hash the password
        this.dateOfBirth = dateOfBirth;
        this.status = "offline"; // Default status is offline
        this.profilePhoto = ""; // Default profile photo path (if any)
        this.coverPhoto = ""; // Default cover photo path (if any)
        this.bio = ""; // Default bio (if any)
        this.posts = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    // Hash password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Getter and setter methods for all fields
    public void updateProfilePhoto(String path) {
        this.profilePhoto = path;
    }

    public void updateCoverPhoto(String path) {
        this.coverPhoto = path;
    }

    public void updateBio(String bio) {
        this.bio = bio;
    }

    public void updatePassword(String newPassword) {
        this.password = hashPassword(newPassword);
    }

    public void addPost(String postContent) {
        this.posts.add(postContent); // You can extend this to handle images, timestamps, etc.
    }

    public void addFriend(String friendId) {
        this.friends.add(friendId); // Add friend's ID or email
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStatus() {
        return status;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public String getBio() {
        return bio;
    }

    public List<String> getPosts() {
        return posts;
    }

    public List<String> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return userId + "," + email + "," + username + "," + password + "," + dateOfBirth + "," + status + "," +
                profilePhoto + "," + coverPhoto + "," + bio;
    }

    // Convert a string into a User object
    public static User fromString(String userData) {
        String[] parts = userData.split(",");
        User user = new User(parts[1], parts[2], parts[3], parts[4]);
        user.status = parts[5];
        user.profilePhoto = parts[6];
        user.coverPhoto = parts[7];
        user.bio = parts[8];
        return user;
    }
}