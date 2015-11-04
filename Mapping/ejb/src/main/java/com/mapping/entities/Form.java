package com.mapping.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import java.util.Date;


/**
 * The persistent class for the form database table.
 * 
 */
@Entity
@Table(name="form")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name = "Form", namespace = "form")
public class Form implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USR_ID")
	private int usrId;
	
	@Column(name="FRM_CREATION_DATE")
	private Date frmCreationDate;

	@Column(name="FRM_DESCRIPTION")
	private String frmDescription;

	@Column(name="FRM_ID")
	private int frmId;

	@Column(name="FRM_STATE")
	private String frmState;

	//bi-directional one-to-one association to Geolocalization
	@OneToOne
	@JoinColumn(name="USR_ID")
	private Geolocalization geolocalization;

	public Form() {
	}

	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public Date getFrmCreationDate() {
		return this.frmCreationDate;
	}

	public void setFrmCreationDate(Date frmCreationDate) {
		this.frmCreationDate = frmCreationDate;
	}

	public String getFrmDescription() {
		return this.frmDescription;
	}

	public void setFrmDescription(String frmDescription) {
		this.frmDescription = frmDescription;
	}

	public int getFrmId() {
		return this.frmId;
	}

	public void setFrmId(int frmId) {
		this.frmId = frmId;
	}

	public String getFrmState() {
		return this.frmState;
	}

	public void setFrmState(String frmState) {
		this.frmState = frmState;
	}

	public Geolocalization getGeolocalization() {
		return this.geolocalization;
	}

	public void setGeolocalization(Geolocalization geolocalization) {
		this.geolocalization = geolocalization;
	}

}