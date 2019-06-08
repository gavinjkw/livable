package com.gavinwilson.livable.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import com.gavinwilson.livable.models.Event;



@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min=2, message="First name must be greater than 1 character")
	private String firstName;
	@Size(min=2, message="Last name must be greater than 1 character")
	private String lastName;
	
	@Email(message="Email must be valid")
	private String email;
    @Size(min=5, message="Password must be greater than 5 characters")
    private String password;
    @Transient
	private String passwordConfirmation;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="houseId")
	private House house;	
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Chore> chores;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Bill> billsCreated;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Event> eventsHosted;
	
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	name = "attends", 
	joinColumns = @JoinColumn(name = "UserId"), 
	inverseJoinColumns = @JoinColumn(name = "EventId")
	)
	private List<Event> events;
    
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
  	private List<Attend> attends;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Supply> supplies;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	name = "debts", 
	joinColumns = @JoinColumn(name = "userId"), 
	inverseJoinColumns = @JoinColumn(name = "billId")
	)
	private List<User> users;

	public User() {
    }
	
	 
	public List<Bill> getBillsCreated() {
		return billsCreated;
	}


	public void setBillsCreated(List<Bill> billsCreated) {
		this.billsCreated = billsCreated;
	}


	public List<Event> getEventsHosted() {
		return eventsHosted;
	}


	public void setEventsHosted(List<Event> eventsHosted) {
		this.eventsHosted = eventsHosted;
	}


	public List<Chore> getChores() {
		return chores;
	}

	public void setChores(List<Chore> chores) {
		this.chores = chores;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Attend> getAttends() {
		return attends;
	}

	public void setAttends(List<Attend> attends) {
		this.attends = attends;
	}

	public List<Supply> getSupplies() {
		return supplies;
	}

	public void setSupplies(List<Supply> supplies) {
		this.supplies = supplies;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}

