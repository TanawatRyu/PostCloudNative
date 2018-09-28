package com.sit.cloudnative.UserService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAdapter{

    public User getUserDetail(Long userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8010/user/"+userId;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    public User[] getAllUserDetail(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8010/users";
        User[] userAll = restTemplate.getForObject(url, User[].class);
        return userAll;
    }

    public User create(User user){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8010/user";
        User userAll = restTemplate.postForObject(url,user,User.class);
        return userAll;
    }
}