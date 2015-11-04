package com.mapping.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the enterprise database table.
 * 
 */
@Entity
@Table(name="enterprise")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Enterprise",namespace="enterprise")
public class Enterprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ENT_ID")
	@XmlElement(name = "entId")
	private int entId;

	
	@Column(name="ENT_CREATION_DATE")
	@XmlElement(name = "entCreationDate")
	private Date entCreationDate;

	@Column(name="ENT_DESCRIPTION")
	@XmlElement(name = "entDescription")
	private String entDescription;

	@Column(name="ENT_NAME")
	@XmlElement(name = "entName")
	private String entName;

	//bi-directional many-to-one association to Group
	@OneToMany(mappedBy="enterprise")
	@XmlElement(name = "groups")
	private List<Group> groups;

	public Enterprise() {
	}

	public int getEntId() {
		return this.entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public Date getEntCreationDate() {
		return this.entCreationDate;
	}

	public void setEntCreationDate(Date entCreationDate) {
		this.entCreationDate = entCreationDate;
	}

	public String getEntDescription() {
		return this.entDescription;
	}

	public void setEntDescription(String entDescription) {
		this.entDescription = entDescription;
	}

	public String getEntName() {
		return this.entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Group addGroup(Group group) {
		getGroups().add(group);
		group.setEnterprise(this);

		return group;
	}

	public Group removeGroup(Group group) {
		getGroups().remove(group);
		group.setEnterprise(null);

		return group;
	}

}