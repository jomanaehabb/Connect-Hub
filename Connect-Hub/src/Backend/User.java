package Backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId, email, username, password, status, bio, profilePhotoPath, coverPhotoPath;
    private LocalDate dateOfBirth;
    private List<Post> posts;
    private List<String> friends;

    public User(String userId, String email, String username, String password, String status, String bio, String profilePhotoPath, String coverPhotoPath, LocalDate dateOfBirth, List<Post> posts, List<String> friends) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
        this.bio = bio;
        this.profilePhotoPath = profilePhotoPath;
        this.coverPhotoPath = coverPhotoPath;
        this.dateOfBirth = dateOfBirth;
        this.posts = posts;
        this.friends = friends;
    }

    public User(String userId, String email, String username, String password, String dateOfBirth) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
       
        this.dateOfBirth = dateOfBirth;
    }



    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getProfilePhotoPath() { return profilePhotoPath; }
    public void setProfilePhotoPath(String profilePhotoPath) { this.profilePhotoPath = profilePhotoPath; }
    public String getCoverPhotoPath() { return coverPhotoPath; }
    public void setCoverPhotoPath(String coverPhotoPath) { this.coverPhotoPath = coverPhotoPath; }
    public List<Post> getPosts() { return posts; }
    public List<String> getFriends() { return friends; }
    
        public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }
}
