package com.example.springproject_03;

import com.example.springproject_03.controller.UserController;
import com.example.springproject_03.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class Springproject03ApplicationTests {

	@InjectMocks
	private UserController userController;

	@Mock
	private RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllUsers(){

		User user = new User(1,"test","testcase","ëmail",null,null,null,null);
		//List<User> users = Arrays.asList(user);

		when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users/",User[].class)).thenReturn(null);

		ResponseEntity<List<User>>  users = userController.getUsers();

		Assertions.assertTrue(users == null);
	}

}
