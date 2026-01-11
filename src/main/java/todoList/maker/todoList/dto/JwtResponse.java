package todoList.maker.todoList.dto;

public class JwtResponse {
  public String token;
  public JwtResponse(String token) {
	  this.token = token;
  }
  public String getToken() {
	  return token;
  }
  public void setToken(String token) {
	  this.token = token;
  }
}
