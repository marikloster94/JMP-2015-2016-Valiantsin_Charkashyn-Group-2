package com.epam.module.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "unit")
public class Unit {

	
	private int unitId;
	private String descr;
	
	private List<Employee> unit = new ArrayList<Employee> ();

	@Id
	@Column (name = "unitId")
	@GeneratedValue
	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	@Column (name="description")
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "unit")
//	@Cascade({CascadeType.SAVE_UPDATE})
	@PrimaryKeyJoinColumn
	public List<Employee> getUnit() {
		return unit;
	}

	public void setUnit(List<Employee> unit) {
		this.unit = unit;
	}
	
	
	
}
