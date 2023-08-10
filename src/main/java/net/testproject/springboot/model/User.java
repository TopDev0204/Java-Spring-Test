package net.testproject.springboot.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "username")
	private String username;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn( name = "user_id")
	List<Task> tasks = new ArrayList<>();
	
	public User() {
		
	}
	
	public User(String first_name, String last_name, String username) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
