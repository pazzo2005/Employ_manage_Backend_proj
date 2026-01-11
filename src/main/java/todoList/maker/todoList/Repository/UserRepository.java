package todoList.maker.todoList.Repository;

import todoList.maker.todoList.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByUsername(String username);

}
