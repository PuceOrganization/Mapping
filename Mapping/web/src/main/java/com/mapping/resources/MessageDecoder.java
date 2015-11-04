package com.mapping.resources;
import org.primefaces.push.Decoder;

import com.mapping.entities.Message;
 

public class MessageDecoder implements Decoder<String,Message> {
	
    //@Override
    public Message decode(String s) {
        String[] userAndMessage = s.split(":");
        
        if (userAndMessage.length >= 2) {
            return new Message().setUserMessage(userAndMessage[0]).setMesText(userAndMessage[1]);
        } 
        else {
            return new Message(s);
        }
    }
}