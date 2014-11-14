package com.kkk.rest.security;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SecurityInterceptor implements Handler<MessageContext> {
	private static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class.getName());


	@Override
	public boolean handleFault(MessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleMessage(MessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
