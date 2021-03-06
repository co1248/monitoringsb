package com.co1248.monitoringsb.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@org.hibernate.annotations.DynamicUpdate
public class Host{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String name;
	private String address;
	private String alive;
	
	private Timestamp regDate, modDate, aliveDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAlive() {
		return alive;
	}
	public void setAlive(String alive) {
		this.alive = alive;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public Timestamp getModDate() {
		return modDate;
	}
	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}
	public Timestamp getAliveDate() {
		return aliveDate;
	}
	public void setAliveDate(Timestamp aliveDate) {
		this.aliveDate = aliveDate;
	}
	public Host(String id, String name, String address, String alive, Timestamp regDate, Timestamp modDate,
			Timestamp aliveDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.alive = alive;
		this.regDate = regDate;
		this.modDate = modDate;
		this.aliveDate = aliveDate;
	}
	
	
}
