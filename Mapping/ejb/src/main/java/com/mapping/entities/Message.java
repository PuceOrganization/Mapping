package com.mapping.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@Table(name="message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MES_ID")
	private int mesId;

	
	@Column(name="MES_SEND_DATE")
	private Date mesSendDate;

	@Column(name="MES_STATE")
	private String mesState;

	@Column(name="MES_TEXT")
	private String mesText;

	//bi-directional many-to-one association to Receptor
	@ManyToOne
	@JoinColumn(name="REC_ID")
	private Receptor receptor;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USR_ID")
	private User user;
	
	private String userMessage;
	
	private boolean updateList;

	public Message() {
	}
	
	public Message(String text){
		this.mesText = text;
	}
	
	public Message(String text, boolean updateList) {
        this.mesText = text;
        this.updateList = updateList;
    }
	
	public Message(String user, String text, boolean updateList) {
        this.mesText = text;
        this.userMessage = user;
        this.updateList = updateList;
    }

	public int getMesId() {
		return this.mesId;
	}

	public void setMesId(int mesId) {
		this.mesId = mesId;
	}

	public Date getMesSendDate() {
		return this.mesSendDate;
	}

	public void setMesSendDate(Date mesSendDate) {
		this.mesSendDate = mesSendDate;
	}

	public String getMesState() {
		return this.mesState;
	}

	public void setMesState(String mesState) {
		this.mesState = mesState;
	}

	public String getMesText() {
		return this.mesText;
	}

	public Message setMesText(String mesText) {
		this.mesText = mesText;
		return this;
	}

	public Receptor getReceptor() {
		return this.receptor;
	}

	public void setReceptor(Receptor receptor) {
		this.receptor = receptor;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public String getUserMessage() {
		return userMessage;
	}



	public Message setUserMessage(String userMessage) {
		this.userMessage = userMessage;
		return this;
	}

	public boolean isUpdateList() {
		return updateList;
	}

	public void setUpdateList(boolean updateList) {
		this.updateList = updateList;
	}
	
}