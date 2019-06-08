package com.gavinwilson.livable.models;

	import java.util.Date;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;
	import javax.persistence.PrePersist;
	import javax.persistence.PreUpdate;
	import javax.persistence.Table;

	import org.springframework.format.annotation.DateTimeFormat;

	@Entity
	@Table(name="debts")
	public class Debt {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private Double amount;
		@Column(updatable=false)
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date createdAt;
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date updatedAt;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="userId")
		private User user;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="billId")
		private Bill bill;
		
		public Debt () {
			
		}
		
		
		
		public Double getAmount() {
			return amount;
		}



		public void setAmount(Double amount) {
			this.amount = amount;
		}



		public Bill getBill() {
			return bill;
		}

		public void setBill(Bill bill) {
			this.bill = bill;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
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

		@PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
	}
