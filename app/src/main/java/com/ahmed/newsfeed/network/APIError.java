package com.ahmed.newsfeed.network;

import androidx.annotation.NonNull;

import java.util.List;

/**
* <h1>APIError</h1>
* <p>Represents an API error</p>
*
* @author Ahmed Fathy
* @since 2019-4-10
* @version 1.0
* */
public class APIError {
    private boolean success;
    private List<String> messages;

    public APIError(boolean success, List<String> messages) {
        this.success = success;
        this.messages = messages;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder error = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            error.append(messages.get(i));
            if(i != messages.size() -1)
                error.append('\n');
        }
        return error.toString();
    }
}
