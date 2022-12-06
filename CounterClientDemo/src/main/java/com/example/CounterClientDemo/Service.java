package com.example.CounterClientDemo;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    HashMap<Integer, Integer> likeCount;

    Service(){
        likeCount = new HashMap<Integer, Integer>();
    }

    public void aggregate(HashMap<String, Integer> counter){
        for(String tweetId : counter.keySet()){
            Integer count = counter.get(tweetId);
            likeCount.put(Integer.parseInt(tweetId),
                    likeCount.getOrDefault(Integer.parseInt(tweetId), 0) + count);
        }
    }

    public int getLikeCount(int id){
        return likeCount.getOrDefault(id, 0);
    }
}
