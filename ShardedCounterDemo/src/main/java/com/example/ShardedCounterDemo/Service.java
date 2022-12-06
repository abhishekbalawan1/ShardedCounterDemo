package com.example.ShardedCounterDemo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    HashMap<Integer, Integer> tweetCounter;
    List<Tweet> tweetList;

    Service(){
        tweetCounter = new HashMap<>();
        tweetList = new LinkedList<>();
    }

    public void createTweet(Tweet tweet){
        tweetList.add(tweet);
        tweetCounter.put(tweet.id, 0);
    }

    public void likeTweet(int id){
        tweetCounter.put(id, tweetCounter.getOrDefault(id, 0)+1);
    }

    public int getLikeCount(int id){
        return tweetCounter.getOrDefault(id, 0);
    }

    public HashMap<Integer, Integer> getTotalLikes(){
        return tweetCounter;
    }
}
