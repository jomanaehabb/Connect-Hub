package Backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public abstract class Manager {
    protected Map<String, User> users = new HashMap<>();
    private static final String DATABASE_FILE = "users.json";

    public Manager() {
        loadUsers();
    }

    public void loadUsers() {
        File file = new File(DATABASE_FILE);
        if (file.exists()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                users = objectMapper.readValue(file, new TypeReference<Map<String, User>>() {});
            } catch (IOException e) {
                System.err.println("Failed to load users: " + e.getMessage());
            }
        }
    }

    public void saveUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(DATABASE_FILE), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
