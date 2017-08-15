package mx.grekz.jog;


import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import mx.grekz.jog.entity.User;

public class JerseyClient {	
	public void getAllUsers() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/v1/users");
		WebTarget details = base.path("/");
		List<User> list = details.request(MediaType.APPLICATION_JSON).get(new GenericType<List<User>>() {});
		
	        list.stream().forEach(item -> 
	        System.out.println(item.getEmail()+", "+ item.getUsername()));
	    
	        client.close();
	}
	public static void main(String[] args) {
		JerseyClient jerseyClient = new JerseyClient();
	    jerseyClient.getAllUsers();
	}

}
