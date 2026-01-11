package todoList.maker.todoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import todoList.maker.todoList.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
   @Autowired
   private UserRepository userRepository;
   @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 todoList.maker.todoList.model.User myUser = userRepository.findByUsername(username)
			   .orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
	   return org.springframework.security.core.userdetails.User
			   .withUsername(myUser.getUsername())
			   .password(myUser.getPassword())
			   .authorities(myUser.getRole())
			   .build();
   }
}
