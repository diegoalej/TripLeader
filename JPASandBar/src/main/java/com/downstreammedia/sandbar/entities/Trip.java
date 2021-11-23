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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy = "trip")
	private Set<Expense> expenses;
	
	@ManyToMany
	@JoinTable(name = "trip_has_location", 
		joinColumns = @JoinColumn(name = "location_id"), 
		inverseJoinColumns = @JoinColumn(name = "trip_id"))
	private List<Location> locations;
	
	@OneToMany(mappedBy = "trip")
	private Set<MealDay> mealSchedule;
	
	@ManyToMany
	@JoinTable(name = "trip_category", 
		joinColumns = @JoinColumn(name = "category_id"), 
		inverseJoinColumns = @JoinColumn(name = "trip_id"))
	private List<Category> categories;
	
	@OneToMany(mappedBy = "trip")
	private Set<UserEquipment> tripgear;
	
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

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Set<MealDay> getMealSchedule() {
		return mealSchedule;
	}

	public void setMealSchedule(Set<MealDay> mealSchedule) {
		this.mealSchedule = mealSchedule;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Set<UserEquipment> getTripgear() {
		return tripgear;
	}

	public void setTripgear(Set<UserEquipment> tripgear) {
		this.tripgear = tripgear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creator, dateEnd, dateStart, id);
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
		return Objects.equals(creator, other.creator) && Objects.equals(dateEnd, other.dateEnd)
				&& Objects.equals(dateStart, other.dateStart) && id == other.id;
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
		builder.append(", locations=");
		builder.append(locations);
		builder.append(", mealSchedule=");
		builder.append(mealSchedule);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", tripgear=");
		builder.append(tripgear);
		builder.append("]");
		return builder.toString();
	}

	public Trip(int id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd,
			Set<User> members, User creator, Set<Expense> expenses, List<Location> locations, Set<MealDay> mealSchedule,
			List<Category> categories, Set<UserEquipment> tripgear) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.members = members;
		this.creator = creator;
		this.expenses = expenses;
		this.locations = locations;
		this.mealSchedule = mealSchedule;
		this.categories = categories;
		this.tripgear = tripgear;
	}

	public Trip() {
		super();
	}
	
}
