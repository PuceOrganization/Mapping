package com.mapping.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USR_ID")
	private int usrId;

	@Column(name="USR_CREATION_DATE")
	private Date usrCreationDate;

	@Column(name="USR_EMAIL")
	private String usrEmail;

	@Column(name="USR_LASTNAME")
	private String usrLastname;

	@Column(name="USR_NAME")
	private String usrName;

	@Column(name="USR_NICK_NAME")
	private String usrNickName;

	@Column(name="USR_NUMBER_ID")
	private int usrNumberId;

	@Column(name="USR_PASSWORD")
	private String usrPassword;
	
	@Column(name="USR_IS_LOG")
	private String usrIsLog;

	@Lob
	@Column(name="USR_PHOTO")
	private byte[] usrPhoto;

	@Column(name="USR_STATE")
	private String usrState;

	//bi-directional one-to-one association to Geolocalization
	@OneToOne(mappedBy="user")
	private Geolocalization geolocalization;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="user")
	private List<Message> messages;

	//bi-directional many-to-one association to Receptor
	@ManyToOne
	@JoinColumn(name="REC_ID")
	private Receptor receptor;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="GRP_ID")
	private Group group;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	public User() {
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public Date getUsrCreationDate() {
		return this.usrCreationDate;
	}

	public void setUsrCreationDate(Date usrCreationDate) {
		this.usrCreationDate = usrCreationDate;
	}

	public String getUsrEmail() {
		return this.usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public String getUsrLastname() {
		return this.usrLastname;
	}

	public void setUsrLastname(String usrLastname) {
		this.usrLastname = usrLastname;
	}

	public String getUsrName() {
		return this.usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrNickName() {
		return this.usrNickName;
	}

	public void setUsrNickName(String usrNickName) {
		this.usrNickName = usrNickName;
	}

	public int getUsrNumberId() {
		return this.usrNumberId;
	}

	public void setUsrNumberId(int usrNumberId) {
		this.usrNumberId = usrNumberId;
	}

	public String getUsrPassword() {
		return this.usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public byte[] getUsrPhoto() {
		return this.usrPhoto;
	}

	public void setUsrPhoto(byte[] usrPhoto) {
		this.usrPhoto = usrPhoto;
	}

	public String getUsrState() {
		return this.usrState;
	}

	public void setUsrState(String usrState) {
		this.usrState = usrState;
	}

	public Geolocalization getGeolocalization() {
		return this.geolocalization;
	}

	public void setGeolocalization(Geolocalization geolocalization) {
		this.geolocalization = geolocalization;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setUser(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setUser(null);

		return message;
	}

	public Receptor getReceptor() {
		return this.receptor;
	}

	public void setReceptor(Receptor receptor) {
		this.receptor = receptor;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

	public String getUsrIsLog() {
		return usrIsLog;
	}

	public void setUsrIsLog(String usrIslog) {
		this.usrIsLog = usrIslog;
	}

}