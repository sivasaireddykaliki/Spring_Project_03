package com.example.springproject_03.controller;

import com.example.springproject_03.entity.Address;
import com.example.springproject_03.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    Logger logger = LogManager.getLogger(UserController.class);

    // Single user
    @RequestMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id)
    {
        logger.info("Single User");
        try {
            String uri= "https://jsonplaceholder.typicode.com/users/"+id;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User> userValue = restTemplate.getForEntity(uri,User.class);
            User user=userValue.getBody();
            logger.info(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }

    //Return address from a user
    @RequestMapping("/user_address/{id}")
    public ResponseEntity<Address> getUserAddress(@PathVariable("id") int id)
    {

        String uri= "https://jsonplaceholder.typicode.com/users/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> userValue = restTemplate.getForEntity(uri,User.class);
        User user=userValue.getBody();
        Address address = user.getAddress();
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    // List of users
    @RequestMapping("/users")
    public ResponseEntity<List<User>> getUsers()
    {
        try {
            String uri = "https://jsonplaceholder.typicode.com/users/";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User[]> userValues=restTemplate.getForEntity(uri,User[].class);
            List<User> users = Arrays.asList(userValues.getBody());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }

    // Address from all users
    @RequestMapping("/users_address")
    public ResponseEntity<List<Address>> getAddresses()
    {
        String uri = "https://jsonplaceholder.typicode.com/users/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> userValues=restTemplate.getForEntity(uri,User[].class);
        List<User> users=Arrays.asList(userValues.getBody());
        List<Address> addresses = users.stream().map(User::getAddress)
                .collect(Collectors.toList());


        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }




}
