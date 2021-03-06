package com.mapping.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")

public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USR_ROL_ID")
	private int usrRolId;

	@Column(name="USR_ROL_CREATION_DATE")
	private Date usrRolCreationDate;

	@Column(name="USR_ROL_STATE")
	private String usrRolState;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROL_ID")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USR_ID")
	private User user;

	public UserRole() {
	}

	public int getUsrRolId() {
		return this.usrRolId;
	}

	public void setUsrRolId(int usrRolId) {
		this.usrRolId = usrRolId;
	}

	public Date getUsrRolCreationDate() {
		return this.usrRolCreationDate;
	}

	public void setUsrRolCreationDate(Date usrRolCreationDate) {
		this.usrRolCreationDate = usrRolCreationDate;
	}

	public String getUsrRolState() {
		return this.usrRolState;
	}

	public void setUsrRolState(String usrRolState) {
		this.usrRolState = usrRolState;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}