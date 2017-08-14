package mx.grekz.jog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResp = (HttpServletResponse) response;
		httpResp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        httpResp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE");
    	httpResp.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type");
        httpResp.setHeader("Access-Control-Expose-Headers", "custom-header1, custom-header2");
    	httpResp.setHeader("Access-Control-Allow-Credentials", "false");
        httpResp.setHeader("Access-Control-Max-Age", "4800");
	    chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
