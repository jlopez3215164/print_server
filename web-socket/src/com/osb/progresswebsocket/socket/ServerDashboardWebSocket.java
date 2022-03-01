package com.osb.progresswebsocket.socket;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Oscar Blancarte <oscarblancarte3@gmail.com>
 */
//@Singleton
@ApplicationScoped
@ServerEndpoint("/progress")
public class ServerDashboardWebSocket {

	@OnOpen
	public void open(Session session) {
		System.out.println("Session opened ==>");
		SessionsHelper.sessions.add(session);
		System.out.println(SessionsHelper.sessions.size());
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("new message ==> " + message);
		try {
			// for (int c = 0; c < 100; c++) {
			for (Session s : SessionsHelper.sessions) {
				System.out.println("Sessiones ->" + s.getId());
				s.getBasicRemote().sendText("{\"value\" : \"" + 1 + "\"}");
				Thread.sleep(100);
			}
			
			// }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Session closed ==>");
		SessionsHelper.sessions.remove(session);
	}

	@OnError
	public void onError(Throwable e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
}
