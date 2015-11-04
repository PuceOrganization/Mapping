package com.mapping.resources;

import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;

import com.mapping.entities.Message;

public final class MessageEncoder implements Encoder<Message, String> {
	 
    //@Override
    public String encode(Message message) {
        return new JSONObject(message).toString();
    }
}
