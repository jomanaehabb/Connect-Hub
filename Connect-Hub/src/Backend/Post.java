package Backend;

import java.time.LocalDateTime;

public class Post {
    private String content;
    private LocalDateTime timestamp;
    private String imagePath;

    public Post(String content, String imagePath) {
        this.content = content;
        this.imagePath = imagePath;
        this.timestamp = LocalDateTime.now();
    }

    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getImagePath() { return imagePath; }
}

