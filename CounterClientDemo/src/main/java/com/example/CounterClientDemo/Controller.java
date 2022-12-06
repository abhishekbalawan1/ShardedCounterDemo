package com.example.CounterClientDemo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate template;

    @Autowired
    private EurekaClient discoveryClient;

    @Autowired
    private Service service;

    @GetMapping("/hello")
    public String hello(){
        return "say hello";
    }

    @PostMapping("/likeTweet/{id}")
    public void likeTweet(@PathVariable int id){

        ServiceInstance serviceInstance = loadBalancer.choose("TWEET-SERVICE");
        String url = serviceInstance.getUri().toString()+"/likeTweet/"+id;
        template.postForLocation(url, Integer.class);

    }

    @GetMapping("/getLikeCount/{id}")
    public int getLikeCount(@PathVariable int id){
        int likeCount = service.getLikeCount(id);
        return likeCount;
    }

    @Scheduled(fixedDelay = 15000)
    public void aggregate(){
        List<InstanceInfo> list = discoveryClient.getInstancesByVipAddress("TWEET-SERVICE", true);
        service = new Service();

        for(InstanceInfo instanceInfo : list){
            String url = instanceInfo.getHomePageUrl()+"/getLikeCount/";
            HashMap<String, Integer> counter = template.getForObject(url, HashMap.class);
            service.aggregate(counter);
        }
    }

}
