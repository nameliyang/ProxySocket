package com.ly.websocket.chat;


import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

@ServerEndpoint(value="/chat"
)
public class ChatServer {

    private static String USERNAME_KEY  = "username";
    private static String USERNAMES_KEY = "usernames";

    private Session session;

    private ServerEndpointConfig endpointConfig;
    private Transcript transcript;

    @OnOpen
    public void startChatChannel(EndpointConfig config,Session session){
        this.endpointConfig = (ServerEndpointConfig) config;
        ChatServerConfigurator  csc = (ChatServerConfigurator) endpointConfig.getConfigurator();
        this.transcript = csc.getTranscript();
        this.session = session;
    }



}
