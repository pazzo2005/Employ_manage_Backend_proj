package todoList.maker.todoList.controller;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import todoList.maker.todoList.Repository.TodoRepository;
import todoList.maker.todoList.Repository.UserRepository;
import todoList.maker.todoList.model.Todo;
import todoList.maker.todoList.model.User;
import todoList.maker.todoList.service.TodoService;
import org.springframework.*;
import org.springframework.data.domain.Pageable;
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TodoRepository todoRepository;
     @Autowired
     private TodoService todoService;
     
     
     
     @PutMapping("/{id}")
     public Todo update(@PathVariable Long id,@RequestBody Todo todo) {
    	 return todoService.update(id,todo);
     }
     @DeleteMapping("/{id}")
     public String delete(@PathVariable Long id) {
    	  todoService.delete(id);
    	  return "Todo with ID"+id+"has been deleted";
    	  }
     @GetMapping
     public Page<Todo> getMyTodos(
    		 @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue ="10") int size,@RequestParam(defaultValue="id") String sortBy,
    		 Principal principal,Authentication authentication) {
    	 Pageable pageable = PageRequest.of(page, size,Sort.by(sortBy).descending());
    	 String username = principal.getName();
    	 boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a-> a.getAuthority().equals("ROLE_ADMIN"));
    	 if(isAdmin) {
    		 return todoRepository.findAll(pageable);
    	 } else{
    		 return todoRepository.findByUser_Username(username,pageable); 
    	 }
     }
     @PostMapping
     public Todo createTodo(@RequestBody Todo todo,Principal principal) {
    	 String username = principal.getName();
    	 User currentUser = userRepository.findByUsername(username)
    			 .orElseThrow(() -> new RuntimeException("User not found"));
    	 todo.setUser(currentUser);
    	 return todoService.saveTodo(todo);
     }
     
}
