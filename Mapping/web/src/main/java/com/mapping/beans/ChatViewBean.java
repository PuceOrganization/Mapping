package com.mapping.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jgroups.protocols.BPING;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import com.mapping.entities.Message;
import com.mapping.entities.User;
import com.mapping.services.UserEjb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
@ManagedBean(name="chatViewBean")
@ViewScoped
public class ChatViewBean implements Serializable {
     
    //private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();
	

	private static final long serialVersionUID = 8252480586821033691L;

	private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
 
    @ManagedProperty("#{chatUsers}")
    private ChatUsersBean users ;
    
    private User username = new User();
    private User privateUser = new User();
 
    private Message privateMessage = new Message();
     
    private Message globalMessage = new Message();
     
//    private String username;
     
    private boolean loggedIn = new Boolean(Boolean.FALSE);
     
//    private String privateUser;
     
    private final static String CHANNEL = "/{room}/";
    
    
    @EJB
    UserEjb userAction;
    
    public ChatUsersBean getUsers() {
        return users;
    }
 
    public void setUsers(ChatUsersBean users) {
        this.users = users;
    }
     
    public User getPrivateUser() {
        return privateUser;
    }
 
    public void setPrivateUser(User privateUser) {
        this.privateUser = privateUser;
    }
 
    public Message getGlobalMessage() {
        return globalMessage;
    }
 
    public void setGlobalMessage(Message globalMessage) {
        this.globalMessage = globalMessage;
    }
 
    public Message getPrivateMessage() {
        return privateMessage;
    }
 
    public void setPrivateMessage(Message privateMessage) {
        this.privateMessage = privateMessage;
    }
     
    public User getUsername() {
        return username;
    }
    
    public void setUsername(User username) {
        this.username = username;
    }
     
    public boolean isLoggedIn() {
    	
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
 
    public void sendGlobal() {
        eventBus.publish(CHANNEL + "*", username.getUsrNickName() + ": " + globalMessage.getMesText());
         
        globalMessage = null;
    }
     
    public void sendPrivate() {
        eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage.getMesText());
         
        privateMessage = null;
    }
    
    public void prueba(){
    	System.out.println("entro");	
		RequestContext requestContext = RequestContext.getCurrentInstance();
        
		User usr = new User();
		usr = userAction.findByName(username.getUsrNickName());
        
        if(users.getUsers().contains(usr)) {
            loggedIn = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
            requestContext.update("growl");
        }
        else {
            users.getUsers().add(username);
            requestContext.execute("PF('subscriber').connect('/" + username + "')");
            loggedIn = true;
        }
    }
     
    public void login() {
    	System.out.println("entro");
	//        RequestContext requestContext = RequestContext.getCurrentInstance();
	//        
	//        
	//        if(users.getUsers().contains(username)) {
	//            loggedIn = true;
	//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
	//            requestContext.update("growl");
	//        }
	//        else {
	//            users.getUsers().add(username);
	//            requestContext.execute("PF('subscriber').connect('/" + username + "')");
	//            loggedIn = true;
	//        }
    }
    
    /*public void pushMessage(ActionEvent action){
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/subscriber",globalMessage);
		System.out.println("message:" + new Date());
	}*/
    
   /* public void receiveMessage(ActionEvent event){
		System.out.println("message:" + new Date());
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		//System.out.println(requestParameterMap);
		String msgFromAdmin= requestParameterMap.get("msgData");
		FacesMessage facesMessage = new FacesMessage("Notificacion",msgFromAdmin);
		facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
		FacesContext.getCurrentInstance().addMessage("growlSubscribe", facesMessage);
	}*/
    
     
    public void disconnect() {
        //remove user and update ui
        users.remove(username);
        RequestContext.getCurrentInstance().update("form:users");
         
        //push leave information
        eventBus.publish(CHANNEL + "*", username + " left the channel.");
         
        //reset state
        loggedIn = false;
        username = null;
    }
}