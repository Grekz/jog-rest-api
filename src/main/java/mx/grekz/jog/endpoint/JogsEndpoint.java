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
import mx.grekz.jog.entity.Jog;
import mx.grekz.jog.entity.User;
import mx.grekz.jog.service.JogService;
import mx.grekz.jog.service.UserService;

@Component
@Path("/users/{userId}/jogs")
@Produces(MediaType.APPLICATION_JSON)
public class JogsEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(JogsEndpoint.class);
	private final String repeatedTemplate = "Trying to create repeated user. With values = [ %s, %s]";
	private static final String MT_JSON = MediaType.APPLICATION_JSON;
	
	@Autowired
	private JogService jogService;	

	@GET
	public Response getAllJogsByUserId(@PathParam("userId") Integer userId) {
		List<Jog> list = jogService.getAllJogsByUserId(userId);
		return list.size() > 0 ? Response.ok(list).build() : Response.noContent().build();
	}
	
	@GET
	@Path("/{jogId}")
	public Response getJogById(@PathParam("userId") Integer userId, @PathParam("jogId") Integer jogId) {
		Jog jog = jogService.getJogById(userId, jogId);
		return Response.ok(jog).build();
	}

	@POST
	@Consumes(MT_JSON)
	public Response addJog(@PathParam("userId") Integer userId, Jog jog) {
		jog.setUserId(userId);
		boolean wasAdded = jogService.addJog(jog);
		if ( !wasAdded ) {
			return Response.status(Status.CONFLICT).build();
		}
		return Response.created(URI.create("/v1/users/" + jog.getUserId() + "/jogs/" + jog.getJogId())).entity(jog).build();
	}
	
	@PUT
	@Path("/{jogId}")
	@Consumes(MT_JSON)
	public Response modifyUser(@PathParam("userId") Integer userId, @PathParam("jogId") Integer jogId, Jog jog) {
		boolean jogExists = jogService.jogExists(jogId);
		if (jogExists) {
			jog.setJogId(jogId);
			jog.setUserId(userId);
			Jog tmp = jogService.getJogById(userId, jogId);
			jog.setCreateDate(tmp.getCreateDate());
			jogService.updateJog(jog);
			return Response.status(Status.ACCEPTED).entity(jog).build();
		}
		return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage("JogID not found")).build();
	}
	
	@DELETE
	@Path("/{jogId}")
	public Response deleteUser(@PathParam("userId") Integer userId, @PathParam("jogId") Integer jogId) {
		if (!jogService.userHasJog(userId, jogId))
			return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("Jog not found for user")).build();
		jogService.deleteJog(jogId);
		return Response.noContent().build();
	}
	
}
