package com.example.ShardedCounterDemo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Tweet {
    int id;
    String content;
    List<Tweet> replies;

    public Tweet(int id, String content, List<Tweet> replies) {
        this.id = id;
        this.content = content;
        this.replies = replies;
    }

    public Tweet(){}

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public List<Tweet> getReplies() {
        return replies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReplies(List<Tweet> replies) {
        this.replies = replies;
    }
}
