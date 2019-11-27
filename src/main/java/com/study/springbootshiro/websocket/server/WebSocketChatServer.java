package com.study.springbootshiro.websocket.server;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.study.springbootshiro.entity.SysUser;
import com.study.springbootshiro.websocket.entity.Message;

@Component
@ServerEndpoint("/websocket/server") // 标记此类为服务端
public class WebSocketChatServer {
	/**
	 * 全部在线会话 PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
	 */
	private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

	/**
	 * 当客户端打开连接：1.添加会话对象 2.更新在线人数
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		String sessionId = (String)httpSession.getAttribute("Authorization");
		System.out.println(sessionId);
		
		

		// onlineSessions.put(session.getId(), session);
		// sendMessageToAll(Message.jsonStr(Message.ENTER, "", "",
		// onlineSessions.size()));
	}

	/**
	 * 公共方法：发送信息给所有人
	 */
	private static void sendMessageToAll(String msg) {
		onlineSessions.forEach((id, session) -> {
			try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
	 * <p>
	 * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
	 */
	@OnMessage
	public void onMessage(Session session, String jsonStr) {
		Message message = JSON.parseObject(jsonStr, Message.class);
		sendMessageToAll(
				Message.jsonStr(Message.SPEAK, message.getUsername(), message.getMsg(), onlineSessions.size()));
	}

	/**
	 * 当关闭连接：1.移除会话对象 2.更新在线人数
	 */
	@OnClose
	public void onClose(Session session) {
		onlineSessions.remove(session.getId());
		sendMessageToAll(Message.jsonStr(Message.QUIT, "", "下线了！", onlineSessions.size()));
	}

	/**
	 * 当通信发生异常：打印错误日志
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}
}
