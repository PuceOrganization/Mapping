package com.mapping.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import com.mapping.entities.Message;
import com.mapping.entities.User;
import com.mapping.services.UserEjb;

@ManagedBean(name="pruebaBean")
@ViewScoped
public class PruebaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1052757441559827774L;
	
    @ManagedProperty("#{chatUsers}")
    private ChatUsersBean users ;
//	
	private User username = new User();
	
    private User privateUser = new User();
 
    private String privateMessage = new String();
     
    private String globalMessage = new String();
	
	private Boolean loggedIn = new Boolean(Boolean.TRUE);
	
	private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
	
	 private final static String CHANNEL = "/{room}/";
	
	@EJB
	UserEjb userAction;
	
	public void prueba(User user){
		System.out.println("Entro");
		System.out.println(user.getUsrName());
	}
	
	public void login(){
		//System.out.println(username.getUsrNickName());
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
        Boolean a;
		
		User usr = new User();
		usr = userAction.findByName(username.getUsrNickName());
		
		for(User aux : users.getUsers()){
			if(aux.getUsrNickName().equals(username.getUsrNickName())){
				loggedIn = Boolean.TRUE;
				requestContext.execute("PF('subscriber').connect('/" + username.getUsrNickName() + "')");
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
				//requestContext.update("growl");
			}
			else{
				loggedIn = Boolean.TRUE;
				
			}
		}
		
		if(loggedIn){
			users.add(username);
			requestContext.execute("PF('subscriber').connect('/" + username.getUsrNickName() + "')");
			loggedIn = Boolean.TRUE;
		}
	}
	
	public void disconnect() {
        //remove user and update ui
        users.remove(username);
        RequestContext.getCurrentInstance().update("pruebaForm:users");
         
        //push leave information
        eventBus.publish(CHANNEL + "*", username.getUsrNickName() + " left the channel.");
         
        //reset state
        loggedIn = false;
        username = new User();
    }
	
	public void sendGlobal() {
        eventBus.publish(CHANNEL + "*", username.getUsrNickName() + ": " + globalMessage);
         
        globalMessage = null;
    }
     
    public void sendPrivate() {
        eventBus.publish(CHANNEL + privateUser, "[PM] " + username.getUsrNickName() + ": " + privateMessage);
         
        privateMessage = null;
    }
	 

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public ChatUsersBean getUsers() {
		return users;
	}

	public void setUsers(ChatUsersBean users) {
		this.users = users;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getPrivateUser() {
		return privateUser;
	}

	public void setPrivateUser(User privateUser) {
		this.privateUser = privateUser;
	}

	public String getPrivateMessage() {
		return privateMessage;
	}

	public void setPrivateMessage(String privateMessage) {
		this.privateMessage = privateMessage;
	}

	public String getGlobalMessage() {
		return globalMessage;
	}

	public void setGlobalMessage(String globalMessage) {
		this.globalMessage = globalMessage;
	}
	

}
