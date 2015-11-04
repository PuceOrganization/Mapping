package com.mapping.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mapping.entities.User;
import com.mapping.services.UserEjb;
 
@ManagedBean(name="chatUsers")
@SessionScoped
public class ChatUsersBean implements Serializable {
     

	private static final long serialVersionUID = 944573236332863673L;
	
	private List<User> userList = new ArrayList<User>();
	
	@EJB
    UserEjb userAction;
	
     
    @PostConstruct
    public void init() {
        try {
			userList = userAction.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ChatUsersBean(){
    }
 
    public List<User> getUsers() {
    	
        return userList;
    }
     
    public void remove(User user) {
        this.userList.remove(user);
    }
     
    public void add(User user) {
        this.userList.add(user);
    }
         
    public boolean contains(User user) {
        return this.userList.contains(user);
    }
}