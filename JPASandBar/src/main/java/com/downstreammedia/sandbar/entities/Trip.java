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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@ManyToMany(mappedBy = "trips")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public LocalDateTime getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDateTime getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	public Location getLocationStart() {
		return locationStart;
	}

	public void setLocationStart(Location locationStart) {
		this.locationStart = locationStart;
	}

	public Location getLocationEnd() {
		return locationEnd;
	}

	public void setLocationEnd(Location locationEnd) {
		this.locationEnd = locationEnd;
	}


	public List<TripEquipment> getGearlist() {
		return gearlist;
	}

	public void setGearlist(List<TripEquipment> gearlist) {
		this.gearlist = gearlist;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<UserEquipment> getUserGear() {
		return userGear;
	}

	public void setUserGear(Set<UserEquipment> userGear) {
		this.userGear = userGear;
	}

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

	public Trip(int id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd,
			Set<User> members, User creator, Set<Expense> expenses, Location locationStart, Location locationEnd,
		    List<TripEquipment> gearlist, Set<Category> categories,
			Set<UserEquipment> userGear) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.members = members;
		this.creator = creator;
		this.expenses = expenses;
		this.locationStart = locationStart;
		this.locationEnd = locationEnd;
		this.gearlist = gearlist;
		this.categories = categories;
		this.userGear = userGear;
	}

	public Trip() {
		super();
	}
		
}
