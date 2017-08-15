package mx.grekz.jog.endpoint;

import java.net.URI;
import java.util.List;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.grekz.jog.entity.ErrorMessage;
import mx.grekz.jog.entity.User;
import mx.grekz.jog.service.UserService;

@Component
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(UsersEndpoint.class);
	private final String repeatedTemplate = "Trying to create repeated user. With values = [ %s, %s]";
	private static final String MT_JSON = MediaType.APPLICATION_JSON;
	
	@Autowired
	private UserService userService;	
	

	@GET
	public Response getAllUsers() {
		List<User> list = userService.getAllUsers();
		return list.size() > 0 ? Response.ok(list).build() : Response.noContent().build();
	}
	
	@GET
	@Path("/{id}")
	public Response getUserById(@PathParam("id") Integer id) {
		User user = userService.getUserById(id);
		return Response.ok(user).build();
	}

	@POST
	@Consumes(MT_JSON)
	public Response addUser(User user) {
		boolean wasAdded = userService.addUser(user);
		if ( !wasAdded ) {
			logger.info(String.format(repeatedTemplate, user.getEmail(), user.getUsername()));
			return Response.status(Status.CONFLICT).build();
		}
		return Response.created(URI.create("/v1/users/" + user.getUserId())).entity(user).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MT_JSON)
	public Response modifyUser(@PathParam("id") Integer id, User user) {
		boolean userExists = userService.userExists(id);
		System.out.println(userExists);
		if (userExists) {
			user.setUserId(id);
			if ( !userService.updateUser(user) )
				return Response.status(Status.NOT_MODIFIED).entity(user).build();
			return Response.status(Status.ACCEPTED).entity(user).build();
		}
		return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage("UserID not found")).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		userService.deleteUser(id);
		return Response.noContent().build();
	}
	
}
