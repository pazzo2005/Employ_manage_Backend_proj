package todoList.maker.todoList.Repository;



import java.util.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import todoList.maker.todoList.model.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo,Long>{
    Page<Todo> findByUser_Username(String username,Pageable pageable);
    Page<Todo> findAll(Pageable pageable);
    
}
