package mx.grekz.jog.endpoint;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import mx.grekz.jog.entity.User;
import mx.grekz.jog.enums.Endpoints;
import mx.grekz.jog.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UsersEndpointTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserById() {
		int userId = 1;
		String url = getUrl() + "/" + userId;
		ResponseEntity<User> usr = restTemplate.getForEntity(url, User.class);
		assertEquals(1, usr.getBody().getUserId());
	}

	@Test
	public void testGetAllUsers() {
		ResponseEntity<User[]> users = restTemplate.getForEntity(getUrl(), User[].class);
		assertNotNull(users.getBody());
		assertTrue(users.getBody().length > 0);
	}

	@Test
	public void testAddUser() {
		ResponseEntity<Object> resp = restTemplate.getForEntity(getUrl(), Object.class);
		//TODO implement assertion
	}

	@Test
	public void testDeleteUser() {
		//TODO implement assertion
	}

	private static String getUrl() {
		return Endpoints.USERS.toString();
	}
}
