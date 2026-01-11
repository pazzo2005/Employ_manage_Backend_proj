package todoList.maker.todoList.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import todoList.maker.todoList.Repository.UserRepository;
import todoList.maker.todoList.model.User;

@Component
public class DataInitializer implements CommandLineRunner {
 @Autowired
 private UserRepository userRepository;
 @Autowired
 private PasswordEncoder passwordEncoder;
 
 @Override
 public void run(String... args) throws Exception {
	 if(userRepository.findByUsername("admin").isEmpty()) {
		 User admin = new User();
		 admin.setUsername("admin");
		 admin.setPassword(passwordEncoder.encode("admin123"));
		 admin.setRole("ROLE_ADMIN");
		 userRepository.save(admin);
		 System.out.println("TEST ACCOUNT: admin / admin123 created in PostGreSql");
	
	 }
	 if(userRepository.findByUsername("emp01").isEmpty() ) {
		 User emp = new User();
		 emp.setUsername("emp01");
		 emp.setPassword(passwordEncoder.encode("emp123"));
		 emp.setRole("ROLE_USER");
		 userRepository.save(emp);
	 }
	 System.out.println("Day 2:Hierarchy Initalized.Roles :ROLE_ADMIN,ROLE_USER");
 }
 
 
}
