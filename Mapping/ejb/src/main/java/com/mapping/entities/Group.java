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
 * The persistent class for the groups database table.
 * 
 */
@Entity
@Table(name="groups")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Group",namespace="group")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GRP_ID")
	@XmlElement(name = "grpId")
	private int grpId;

	
	@Column(name="GRP_CREATION_DATE")
	@XmlElement(name = "grpCreationDate")
	private Date grpCreationDate;

	@Column(name="GRP_DESCRIPTION")
	@XmlElement(name = "grpDescription")
	private String grpDescription;

	@Column(name="GRP_NAME")
	@XmlElement(name = "grpName")
	private String grpName;

	@Column(name="GRP_STATE")
	@XmlElement(name = "grpState")
	private String grpState;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	@JoinColumn(name="ENT_ID")
	private Enterprise enterprise;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="group")
	private List<User> users;

	public Group() {
	}

	public int getGrpId() {
		return this.grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	public Date getGrpCreationDate() {
		return this.grpCreationDate;
	}

	public void setGrpCreationDate(Date grpCreationDate) {
		this.grpCreationDate = grpCreationDate;
	}

	public String getGrpDescription() {
		return this.grpDescription;
	}

	public void setGrpDescription(String grpDescription) {
		this.grpDescription = grpDescription;
	}

	public String getGrpName() {
		return this.grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public String getGrpState() {
		return this.grpState;
	}

	public void setGrpState(String grpState) {
		this.grpState = grpState;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setGroup(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setGroup(null);

		return user;
	}

}