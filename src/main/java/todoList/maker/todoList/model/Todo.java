package todoList.maker.todoList.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;import jakarta.persistence.JoinColumn;import jakarta.persistence.ManyToOne;import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="todos")
@Data

public class Todo {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;
  @Column(nullable=false)
  @NotBlank(message="Title cannot be empty")
  @Size(min =2,message="Title must be at least 2 characters")
  private String title;
  private boolean completed=false;  private String description;  @ManyToOne  @JoinColumn(name="user_id")  private User user;      
  public String getDescription() {
	return description;
}
  public void setDescription(String description) {
	this.description = description;
  }
  public long getId() {
	return id;
  }
  public void setId(long id) {
	this.id = id;
  }
  public String getTitle() {
	return title;
  }
  public void setTitle(String title) {
	this.title = title;
  }
  public boolean isCompleted() {
	return completed;
  }
  public void setCompleted(boolean completed) {
	this.completed = completed;
  }  public void setUser(User user) {	// TODO Auto-generated method stub	this.user = user;}
  
    
}
