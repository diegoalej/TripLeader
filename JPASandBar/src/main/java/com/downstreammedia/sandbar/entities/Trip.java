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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Trip {
	
	/*********FIELDS*********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name="date_start")
	private LocalDateTime dateStart;
	
	@Column(name="date_end")
	private LocalDateTime dateEnd;
	
	//@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_trip", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "trip_id"))
	private Set<User> members;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User creator;
	
	@JsonIgnore
	@OneToMany(mappedBy = "trip")
	private Set<Expense> expenses;
	
	@OneToOne
	@JoinColumn(name = "startloc_id")
	private Location locationStart;

	@OneToOne
	@JoinColumn(name = "endloc_id")
	private Location locationEnd;
	
	@JsonIgnore
	@OneToMany(mappedBy = "trip")
	private List<TripEquipment> gearlist;
	
	@JsonIgnore
	@OneToMany(mappedBy = "trip")
	private Set<Category> categories;
	
	@JsonIgnore
	@OneToMany(mappedBy = "trip")
	private Set<UserEquipment> userGear;

	
	/*********METHODS*********/
	@Override
	public int hashCode() {
		return Objects.hash(dateEnd, dateStart, id, locationEnd, locationStart);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		return Objects.equals(dateEnd, other.dateEnd) && Objects.equals(dateStart, other.dateStart) && id == other.id
				&& Objects.equals(locationEnd, other.locationEnd) && Objects.equals(locationStart, other.locationStart);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trip [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateStart=");
		builder.append(dateStart);
		builder.append(", dateEnd=");
		builder.append(dateEnd);
		builder.append(", members=");
		builder.append(members);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", expenses=");
		builder.append(expenses);
		builder.append(", locationStart=");
		builder.append(locationStart);
		builder.append(", locationEnd=");
		builder.append(locationEnd);
		builder.append(", gearlist=");
		builder.append(gearlist);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", userGear=");
		builder.append(userGear);
		builder.append("]");
		return builder.toString();
	}
		
}
