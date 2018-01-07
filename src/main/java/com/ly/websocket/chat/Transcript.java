package com.ly.websocket.chat;

import java.util.ArrayList;
import java.util.List;

public class Transcript {

    private List<String> messages = new ArrayList<>();
    private List<String> usernames = new ArrayList<>();
    private int maxLines;

    Transcript(int maxLines){
        this.maxLines = maxLines;
    }

    public void addEntry(String username,String message){
        if(usernames.size() > maxLines){
            usernames.remove(0);
            messages.remove(0);
        }
        usernames.add(username);
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public List<String> getUsernames() {
        return usernames;
    }
}
