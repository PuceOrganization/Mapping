package com.mapping.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

import com.mapping.entities.Enterprise;
import com.mapping.entities.Role;
import com.mapping.entities.User;
import com.mapping.entities.UserRole;
import com.mapping.services.EnterpriseEjb;

@ManagedBean(name="enterpriseBean")
@SessionScoped
public class EnterpriseBean implements Serializable{

	//4545asdasdasdasd
	private static final long serialVersionUID = 71272475901124242L;
	
	
	private Enterprise enterprise = new Enterprise();
	private Enterprise enterpriseSearch = new Enterprise();
	
	
	private Calendar cal = Calendar.getInstance();
	
	
	private String regexText = new String();
	
	
	//asdasd

//SDASDASD

	public void setEnterpriseList(List<Enterprise> enterpriseList) {
		this.enterpriseList = enterpriseList;
	}

	private List<Enterprise> enterpriseList = new ArrayList<Enterprise>();
	
	private Boolean isNew = new Boolean(Boolean.TRUE);
	
	@EJB
	EnterpriseEjb enterpriseAction;
	
	@PostConstruct
	public void initialize(){
		 enterpriseList = new ArrayList<Enterprise>();
		 try {
			enterpriseList = enterpriseAction.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void save(){
		
			if(isNew){
				try {	
					
					
					enterprise.setEntCreationDate(cal.getTime());
					enterpriseAction.persist(enterprise);
					
					enterprise= new Enterprise();
					
					RequestContext.getCurrentInstance().update("enterpriseForm:insertDialog");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					enterpriseAction.merge(enterprise);
					
					isNew = Boolean.TRUE;
					enterprise = new Enterprise();
					
					RequestContext.getCurrentInstance().update("enterpriseForm:insertDialog");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				enterpriseList = enterpriseAction.findAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestContext.getCurrentInstance().update("enterpriseForm:enterpriseTable");
		}
		
		
	

	
	public void search(){
		try {
		//cambio
			enterpriseList = enterpriseAction.findByEnterpriseName(enterpriseSearch);
			RequestContext.getCurrentInstance().update("enterpriseForm:enterpriseTable");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void delete(Enterprise ent){
		
		
		try {
			enterpriseAction.remove(ent);
			enterpriseList = enterpriseAction.findAll();
			RequestContext.getCurrentInstance().update("enterpriseForm:enterpriseTable");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clean(){
		enterpriseList = new ArrayList<Enterprise>();
		enterpriseSearch= new Enterprise();
		RequestContext.getCurrentInstance().update("enterpriseForm:enterpriseTable");
		RequestContext.getCurrentInstance().update("enterpriseForm:enterpriseSearchInput");
	}
	
	public void edit(Enterprise ent){
		isNew = Boolean.FALSE;
		enterprise = new Enterprise();
		enterprise= ent;
		//prueba
		RequestContext.getCurrentInstance().update("enterpriseForm:insertDialog");
		RequestContext.getCurrentInstance().execute("PF('dlg2').show();");
	}
	
	public void handleClose(CloseEvent event) {
		enterprise = new Enterprise();
		
		isNew = Boolean.TRUE;
		RequestContext.getCurrentInstance().update("enterpriseForm:insertDialog");
    }
	

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	

	public String getRegexText() {
		return regexText;
	}

	public void setRegexText(String regexText) {
		this.regexText = regexText;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}




	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}




	public Enterprise getEnterpriseSearch() {
		return enterpriseSearch;
	}




	public void setEnterpriseSearch(Enterprise enterpriseSearch) {
		this.enterpriseSearch = enterpriseSearch;
	}




	public Calendar getCal() {
		return cal;
	}




	public void setCal(Calendar cal) {
		this.cal = cal;
	}




	public List<Enterprise> getEnterpriseList() {
		return enterpriseList;
	}
	

	
	

}
