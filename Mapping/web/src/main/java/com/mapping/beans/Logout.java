package com.mapping.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabCloseEvent;

import com.mapping.entities.User;
import com.mapping.services.UserEjb;


@ManagedBean(name="loginBean")
@SessionScoped
public class Logout implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5722616030335589264L;
	
	User user = new User();
	User userTable = new User();
	private List<User> userList = new ArrayList<User>();
	private List<User> userNotificationList = new ArrayList<User>();
	
	@EJB
	UserEjb userAction;

	public Logout(){
	
	}
	
	public void log(){
		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		 RequestContext requestContext = RequestContext.getCurrentInstance();
		 user = userAction.findByName( ec.getUserPrincipal().getName());
		 //requestContext.execute("PF('subscriber').connect('/" + user.getUsrNickName() + "')");
		 RequestContext.getCurrentInstance().execute("PF('bar').show();");
	}
	
	public void chargeList(){
		try {
			userList = userAction.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//onclick="PF('bar').show()"
		RequestContext.getCurrentInstance().update("notificationForm:userNotificationTable");
	}
	
	public void onRowSelect(SelectEvent event) {
	    //    FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
	    //    FacesContext.getCurrentInstance().addMessage(null, msg);
		if(userNotificationList.isEmpty())
			userNotificationList.add((User)event.getObject());
		else{
			for(User aux : userNotificationList){
				if(aux.getUsrId()!=((User)event.getObject()).getUsrId()){
					userNotificationList.add((User)event.getObject());
					RequestContext.getCurrentInstance().update("notificationForm:tab");
					break;
				}
			}
		}
		RequestContext.getCurrentInstance().update("notificationForm:tab");
    }
	
	public void onTabClose(TabCloseEvent event) {
//	        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("se cerro");
	}
	/*marik*/
	/*marik vos*/
	public void logout() throws IOException {
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    System.out.println(ec.getUserPrincipal().getName());
	    ec.invalidateSession();
	   
	    ec.redirect(ec.getRequestContextPath() + "/pages/user/user.xhtml");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserNotificationList() {
		return userNotificationList;
	}

	public void setUserNotificationList(List<User> userNotificationList) {
		this.userNotificationList = userNotificationList;
	}

	public User getUserTable() {
		return userTable;
	}

	public void setUserTable(User userTable) {
		this.userTable = userTable;
	}
}
