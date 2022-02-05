package com.downstreammedia.sandbar.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;


@Entity
public class User {
	
	/*********FIELDS*********/
	
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "creator")
	private List<Trip> createdTrips;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_trip", 
		joinColumns = @JoinColumn(name = "trip_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<Trip> trips;
	
	@JsonIgnore	
	@OneToMany(mappedBy = "creator")
	private Set<Expense> expenses;

	@JsonIgnore
	@OneToMany(mappedBy = "creator")
	private Set<UserEquipment> equipment;
	
	/*********METHODS*********/

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

	public List<Trip> getCreatedTrips() {
		return createdTrips;
	}

	public void setCreatedTrips(List<Trip> createdTrips) {
		this.createdTrips = createdTrips;
	}

	public Set<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}

	public Set<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	public Set<UserEquipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(Set<UserEquipment> equipment) {
		this.equipment = equipment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateStart, email, id);
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
		return Objects.equals(dateStart, other.dateStart) && Objects.equals(email, other.email) && id == other.id;
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
		builder.append(", createdTrips=");
		builder.append(createdTrips);
		builder.append(", trips=");
		builder.append(trips);
		builder.append(", expenses=");
		builder.append(expenses);
		builder.append(", equipment=");
		builder.append(equipment);
		builder.append("]");
		return builder.toString();
	}

	public User(int id, String username, String password, String email, String role, boolean active, String imageUrl,
			LocalDateTime dateUpdated, LocalDateTime dateStart, List<Trip> createdTrips, Set<Trip> trips,
			Set<Expense> expenses, Set<UserEquipment> equipment) {
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
		this.createdTrips = createdTrips;
		this.trips = trips;
		this.expenses = expenses;
		this.equipment = equipment;
	}

	public User() {
		super();
	}
	
}