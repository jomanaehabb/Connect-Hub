package com.mycompany.lab9;

import java.time.LocalDateTime;

public class Post extends Content{

    public Post(String contentID, String authorID, InternalContent content, LocalDateTime timeStamp) {
        super(contentID, authorID, content, timeStamp);
    }
}
