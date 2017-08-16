package mx.grekz.jog.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import mx.grekz.jog.endpoint.JogsEndpoint;
import mx.grekz.jog.endpoint.UsersEndpoint;

@Component
@ApplicationPath("/v1")
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig() {
		register(UsersEndpoint.class);
		register(JogsEndpoint.class);
	}
}
