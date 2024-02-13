package com.example.springproject_03;

import com.example.springproject_03.controller.UserController;
import com.example.springproject_03.entity.Address;
import com.example.springproject_03.entity.Geo;
import com.example.springproject_03.entity.User;
import com.example.springproject_03.service.UserService;
import com.example.springproject_03.testhelper.TestHelper;
import com.example.springproject_03.testhelper.UserServiceTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class Springproject03ApplicationTests {

	@InjectMocks // if you want to test methods in UserService class.
	private UserController userController;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks // if you want to test methods in UserService class.
	private UserService userService;

	private TestHelper testHelper = new TestHelper();

	private UserServiceTest userServiceTest = new UserServiceTest();

	@Test
	void contextLoads() {
	}

	@BeforeEach
	private void setup() {

		//ReflectionTestUtils.setField(userController,"restTemplate",restTemplate);
		// ReflectionTestUtils.setField(userService,"restTemplate",restTemplate);
	}

	@Test
	public void testGetUserAddress(){


		String url = testHelper.getUrl("1");

		User user1 = testHelper.getDummyUser("adam");
		//List<User> users = Arrays.asList(user);

		// mimic or mock the test function
		when(restTemplate.getForEntity(url,User.class)).thenReturn(new ResponseEntity<>(user1, HttpStatus.OK));

		ResponseEntity<Address>  userAddress = userController.getUserAddress(1);

		Assertions.assertNotNull(userAddress);

		Assertions.assertEquals("Delhi",userAddress.getBody().getCity());

		Assertions.assertTrue(userAddress.getBody().getStreet().equals("ABC"));

		Assertions.assertNotNull(userAddress.getBody().getGeo());

		//Assertions.ass
	}

	@Test
	public void testGetAllAddresses(){


		String url=testHelper.getUrl(null);
		User user1 = testHelper.getDummyUser("adam");
		User user2 = testHelper.getDummyUser("ram");
		User user3 = testHelper.getDummyUser("ravi");

		User[] users = new User[3];
		users[0]=user1;
		users[1]=user2;
		users[2]=user3;

		when(restTemplate.getForEntity(url,User[].class)).thenReturn(new ResponseEntity<>(users,HttpStatus.OK));

		ResponseEntity<List<Address>> addresses = userController.getAddresses();
		addresses.getBody().size();

		Assertions.assertEquals(3,addresses.getBody().size());

		Assertions.assertNotNull(addresses);

		Assertions.assertNotSame(addresses.getBody().get(0),addresses.getBody().get(1),"Not same"); // not same object references

		// Assertions.ass
	}

	@Test
	public void testAllUsers()
	{
		String url=  testHelper.getUrl(null);
		User user1 = testHelper.getDummyUser("adam");
		User user2 = testHelper.getDummyUser("ram");
		User user3 = testHelper.getDummyUser("ravi");

		User[] users = new User[3];
		users[0]=user1;
		users[1]=user2;
		users[2]=user3;

		when(restTemplate.getForEntity(url,User[].class)).thenReturn(new ResponseEntity<>(users, HttpStatus.OK));

		ResponseEntity<List<User>> users_ = userService.getUsers();

		Assertions.assertNotNull(users_);

		Assertions.assertEquals(3, users_.getBody().size()); // 3 users or not

		Assertions.assertNotSame(users_.getBody().get(0),users_.getBody().get(1),"Not same");


	}

	@Test
	public void testUser(){

		String url = testHelper.getUrl("1");

		User user1 = testHelper.getDummyUser("adam");
		//List<User> users = Arrays.asList(user);

		when(restTemplate.getForEntity(url,User.class)).thenReturn(new ResponseEntity<>(user1, HttpStatus.OK));

		ResponseEntity<User>  user = userService.getUser(1);

		Assertions.assertNotNull(user);

		Assertions.assertEquals("adam", user.getBody().getName());

		Assertions.assertNotNull(user.getBody().getAddress());

	}


}
