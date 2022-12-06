package com.example.ShardedCounterDemo;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    Service service;

    @PostMapping("/createTweet")
    public int createTweet(@RequestBody Tweet tweet){
        service.createTweet(tweet);
        return tweet.id;
    }

    @PostMapping("/likeTweet/{id}")
    public void likeTweet(@PathVariable int id){
        service.likeTweet(id);
    }

    @GetMapping("/getLikeCount/{id}")
    public int getLikeCount(@PathVariable int id){
        return service.getLikeCount(id);
    }

    @GetMapping("/getLikeCount")
    public HashMap<Integer, Integer> getTotalLikeCount(){
        return service.getTotalLikes();
    }

}
