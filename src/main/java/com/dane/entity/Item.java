package com.dane.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_item")
public class Item implements java.io.Serializable {

	private int id;
	private String name;
	private int price;
	private int stock;
	
	private Set<Inventory> inventory;
	private Set<Order> order;

	public Item() {
	}

	public Item(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price")
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Column(name = "stock")
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Item {" + "id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "}";
	}
	
	@OneToMany(mappedBy="Item",cascade=CascadeType.ALL)
	public Set<Inventory> getInventory() {
        return inventory;
    }
	
	@OneToMany(mappedBy="Item",cascade=CascadeType.ALL)
	public Set<Order> getOrder() {
        return order;
    }
}
