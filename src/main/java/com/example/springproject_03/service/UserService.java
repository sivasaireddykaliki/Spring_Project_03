package com.example.springproject_03.service;

import com.example.springproject_03.controller.UserController;
import com.example.springproject_03.entity.Address;
import com.example.springproject_03.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

     Logger logger = LogManager.getLogger(UserService.class);

     private RestTemplate restTemplate = new RestTemplate();


     public ResponseEntity<User> getUser(int id)
     {

          logger.info("Single User");
          try {
               String uri= "https://jsonplaceholder.typicode.com/users/"+id;
               ResponseEntity<User> userValue = restTemplate.getForEntity(uri,User.class);
               User user=userValue.getBody();
               logger.info(user);
               return new ResponseEntity<>(user, HttpStatus.OK);
          }
          catch (RestClientException e) {
               throw new RuntimeException(e);
          }

     }

     public ResponseEntity<List<User>> getUsers()
     {
          logger.info("List of users");
          try {
               String uri = "https://jsonplaceholder.typicode.com/users/";
               ResponseEntity<User[]> userValues=restTemplate.getForEntity(uri,User[].class);
               List<User> users = Arrays.asList(userValues.getBody());
               return new ResponseEntity<>(users, HttpStatus.OK);
          } catch (RestClientException e) {
               throw new RuntimeException(e);
          }

     }



}
