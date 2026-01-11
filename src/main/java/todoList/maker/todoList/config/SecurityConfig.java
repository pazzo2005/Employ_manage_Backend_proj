package todoList.maker.todoList.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import todoList.maker.todoList.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
   @Bean
   public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
   }
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
       http.csrf(csrf->csrf.disable())
       .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .authorizeHttpRequests(auth->auth.anyRequest().permitAll());
       // 7. LOGIC FIX: Insert the guard only for protected routes
       http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
   }
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	   return authConfig.getAuthenticationManager();
   }
}
