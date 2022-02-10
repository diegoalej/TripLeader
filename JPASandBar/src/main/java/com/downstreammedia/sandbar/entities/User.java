package com.downstreammedia.sandbar.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
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
	@ManyToMany(cascade = CascadeType.MERGE)
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
}