package todoList.maker.todoList.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends org.springframework.web.filter.OncePerRequestFilter{
   @Autowired
   private JwtUtils jwtUtils;
   @Autowired
   private UserDetailsService userDetailsService;
   @Override
   protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
	   String path = request.getServletPath();
	   if(path.startsWith("/api/auth/")) {
		   filterChain.doFilter(request, response);
		   return;
	   }
	   String authHeader = request.getHeader("Authorization");
	   if(authHeader != null &&authHeader.startsWith("Bearer ")) {
		   String jwt = authHeader.substring(7);
		   if(jwtUtils.ValidateToken(jwt)) {
			   String username = jwtUtils.getUsernameFromToken(jwt);
			   UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			   UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			   authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			   SecurityContextHolder.getContext().setAuthentication(authentication);
		   }
	   }
	   filterChain.doFilter(request, response);
   }
}
