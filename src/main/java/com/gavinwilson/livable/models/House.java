package com.gavinwilson.livable.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="houses")
public class House {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer pin;
	@Transient
	private Integer pinConfirmation;
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
	
    @OneToMany(mappedBy="house", fetch = FetchType.LAZY)
	private List<User> users;
    
    @OneToMany(mappedBy="house", fetch = FetchType.LAZY)
	private List<HouseMessage> houseMessages;
    
    @OneToMany(mappedBy="house", fetch = FetchType.LAZY)
	private List<Event> events;
    
    @OneToMany(mappedBy="house", fetch = FetchType.LAZY)
	private List<Chore> chores;
    
    @OneToMany(mappedBy="house", fetch = FetchType.LAZY)
	private List<Bill> bills;
	
	public House () {
		
	}
	
	
	public List<Bill> getBills() {
		return bills;
	}


	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}


	public List<HouseMessage> getHouseMessages() {
		return houseMessages;
	}


	public void setHouseMessages(List<HouseMessage> houseMessages) {
		this.houseMessages = houseMessages;
	}


	public List<Event> getEvents() {
		return events;
	}


	public void setEvents(List<Event> events) {
		this.events = events;
	}


	public List<Chore> getChores() {
		return chores;
	}


	public void setChores(List<Chore> chores) {
		this.chores = chores;
	}


	public Integer getPinConfirmation() {
		return pinConfirmation;
	}


	public void setPinConfirmation(Integer pinConfirmation) {
		this.pinConfirmation = pinConfirmation;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPin() {
		return pin;
	}


	public void setPin(Integer pin) {
		this.pin = pin;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
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
