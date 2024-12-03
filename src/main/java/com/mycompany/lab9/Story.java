package com.mycompany.lab9;

import java.time.LocalDateTime;

public class Story extends Content{
    private LocalDateTime endTime;

    public Story(String contentID, String authorID, InternalContent content, LocalDateTime timeStamp) {
        super(contentID, authorID, content, timeStamp);
        endTime = timeStamp.plusDays(1);
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
