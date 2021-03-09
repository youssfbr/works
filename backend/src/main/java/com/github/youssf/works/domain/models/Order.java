package com.github.youssf.works.domain.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.youssf.works.domain.model.enums.OrderStatus;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private BigDecimal price;
	private Instant openingDate;
	private LocalDateTime endDate;
	private OrderStatus status;
	
	@ManyToOne    
	private Client client;
	
	@OneToMany(mappedBy = "order")	
	private List<Commentation> commentations = new ArrayList<>();
	
	public Order() {	
	}

	public Order(Long id, String description, BigDecimal price, Instant openingDate, LocalDateTime endDate,
			OrderStatus status, Client client) {		
		this.id = id;
		this.description = description;
		this.price = price;
		this.openingDate = openingDate;
		this.endDate = endDate;
		this.status = status;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Instant getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Instant openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	

	public List<Commentation> getCommentations() {
		return commentations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
