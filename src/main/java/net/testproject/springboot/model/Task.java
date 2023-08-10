package net.testproject.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private Boolean status = false;
	
	@Column(name = "date_time")
	private String date_time;
	
	public Task() {
		
	}
	
	public Task(String name, String description, String date_time) {
		super();
		this.name = name;
		this.description = description;
		this.date_time = date_time;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateTime() {
		return date_time;
	}
	public void setDateTime(String date_time) {
		this.date_time = date_time;
	}
	public Boolean getStatus(){
		return status;
	}
	public void setStatus(Boolean status){
		this.status = status;
	}
}
