package com.ly.websocket;

import com.sun.deploy.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class MyWebSocket {

	private Session session;
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		System.out.println(session.getId() + " has opened a connection");
		try {
			session.getBasicRemote().sendText("链接已经建立");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@OnClose
	public void onClose(){
	}


	AtomicInteger count = new AtomicInteger();
	@OnMessage
	public void incoming(String message) throws IOException {
		System.out.println(message);
		if("".equals(message)){
			session.getBasicRemote().sendText("空");
			return ;
		}
		session.getBasicRemote().sendText(new StringBuilder(count.incrementAndGet()+":"+ message).reverse().toString());
	}
}
