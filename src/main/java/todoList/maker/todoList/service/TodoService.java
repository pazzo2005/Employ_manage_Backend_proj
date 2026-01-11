package todoList.maker.todoList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import todoList.maker.todoList.Repository.TodoRepository;
import todoList.maker.todoList.model.Todo;
@Service
@Data
public class TodoService {
 @Autowired
 private TodoRepository todoRepository;
 public List<Todo> getAll() {
	 return todoRepository.findAll();
 }
 public Todo create(Todo todo) {
	 return todoRepository.save(todo);
 }
 public Todo update(Long id, Todo todo) {
	// TODO Auto-generated method stub
	 return todoRepository.findById(id)
	            .map(existingTodo -> {
	                // 1. Corrected 'setTittle' to 'setTitle'
	                // 2. Changed 'updatedTodo' to 'todo' (to match your parameter)
	                existingTodo.setTitle(todo.getTitle()); 
	                existingTodo.setCompleted(todo.isCompleted());
	                
	                // 3. Corrected 'todoRespository' to 'todoRepository'
	                return todoRepository.save(existingTodo);
	            })
	            // 4. Corrected 'RunTimeException' to 'RuntimeException'
	            .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
 }
 public void delete(Long id) {
	// TODO Auto-generated method stub
	 todoRepository.deleteById(id);
	
 }
 public List<Todo> getAllTodos() {
	// TODO Auto-generated method stub
	 return todoRepository.findAll();
	
 }
 public Todo saveTodo(Todo todo) {
	// TODO Auto-generated method stub
	 
	return todoRepository.save(todo);
 }
}
