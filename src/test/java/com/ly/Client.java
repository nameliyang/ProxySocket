package com.ly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Client {
	
	private static String uri = "ws://localhost/echo";
	
	
	private static Session session;

	private void start() {
		WebSocketContainer container = null;
		try {
			container = ContainerProvider.getWebSocketContainer();
		} catch (Exception ex) {
			System.out.println("error" + ex);
		}

		try {
			URI r = URI.create(uri);
			session = container.connectToServer(WebSocketClient.class, r);
		} catch (DeploymentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			do {
				input = br.readLine();
				if (!input.equals("exit"))
					client.session.getBasicRemote().sendText(input);
			} while (!input.equals("exit"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

