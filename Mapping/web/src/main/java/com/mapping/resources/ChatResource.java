package com.mapping.resources;

import org.atmosphere.util.ServletContextFactory;
import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mapping.beans.ChatUsersBean;
import com.mapping.entities.Message;
import com.mapping.entities.User;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.ServletContext;

//@ManagedBean
@PushEndpoint(value="/{room}/{user}")
@Singleton
public class ChatResource {
 
    
	private final Logger logger = LoggerFactory.getLogger(ChatResource.class);
//    
//   @OnMessage(encoders = {JSONEncoder.class})
//   public String onMessage(String message) {
//       return message;
//   }
    
 
    @PathParam("room")
    private String room;
 
    @PathParam("user")
    private String username;
 
    private ServletContext ctx = ServletContextFactory.getDefault().getServletContext();
    
//   @Inject
//    private ServletContext ctx;
// 
    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus) {
    	logger.info("OnOpen {}", r);
 
        eventBus.publish(room + "/*", new Message(String.format("%s has entered the room '%s'",  username, room), true));
        //eventBus.publish(room + "/*", new Message("entro", true));
    }
 
    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus) {
       // ChatUsersBean users= (ChatUsersBean) ctx.getAttribute("chatUsers");
       // users.remove(username);
         
        eventBus.publish(room + "/*", new Message(String.format("%s has left the room", username), true));
    }
 
    @OnMessage(decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
    public Message onMessage(Message message) {
        return message;
    }
 
}