package com.downstreammedia.sandbar.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	//FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String role;
	
	private boolean active;
	
	@Column(name="img_url")
	private String imageUrl;
	
	@Column(name="date_updated")
	private LocalDateTime dateUpdated;

	@Column(name="date_start")
	private LocalDateTime dateStart;

	
	
	//METHODS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public LocalDateTime getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, dateStart, dateUpdated, email, id, imageUrl, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return active == other.active && Objects.equals(dateStart, other.dateStart)
				&& Objects.equals(dateUpdated, other.dateUpdated) && Objects.equals(email, other.email)
				&& id == other.id && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", role=");
		builder.append(role);
		builder.append(", active=");
		builder.append(active);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append(", dateStart=");
		builder.append(dateStart);
		builder.append("]");
		return builder.toString();
	}

	public User(int id, String username, String password, String email, String role, boolean active, String imageUrl,
			LocalDateTime dateUpdated, LocalDateTime dateStart) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.active = active;
		this.imageUrl = imageUrl;
		this.dateUpdated = dateUpdated;
		this.dateStart = dateStart;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	

}