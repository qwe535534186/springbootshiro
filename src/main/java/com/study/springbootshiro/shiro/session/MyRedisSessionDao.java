package com.study.springbootshiro.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.crazycake.shiro.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 自定义redisSessionDao 目前未做更改
 * @author qy
 * @date 2019年11月12日 下午1:27:15
 */
public class MyRedisSessionDao extends RedisSessionDAO {
	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
	/**
	 * shiro-redis的session对象前缀
	 */
	private RedisManager redisManager;

	/**
	 * The Redis key prefix for the sessions
	 */
	private String keyPrefix = "shiro_redis_session:";

	@Override
	public void update(Session session) throws UnknownSessionException {
		System.out.println("******* update  ==> " + session.getId());
		this.saveSession(session);
	}

	/**
	 * save session
	 * 
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException {
		System.out.println("******* save  ==> " + session.getId());
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		byte[] key = getByteKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);
		session.setTimeout(redisManager.getExpire() * 1000);
		this.redisManager.set(key, value, redisManager.getExpire());
	}

	@Override
	public void delete(Session session) {
		System.out.println("******* delete  ==> " + session.getId());
		redisManager.del(this.getByteKey(session.getId()));
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		System.out.println("******* getActiveSessions ");
		Set<Session> sessions = new HashSet<Session>();

		Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
		if (keys != null && keys.size() > 0) {
			for (byte[] key : keys) {
				Session s = (Session) SerializeUtils.deserialize(redisManager.get(key));
				sessions.add(s);
			}
		}

		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		System.out.println("******* doCreate  ==> " + session.getId());
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId){
		System.out.println("******* doReadSession  ==> " + sessionId);
		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}
		Session s = (Session) SerializeUtils.deserialize(redisManager.get(this.getByteKey(sessionId)));
		return s;
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId) {
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}

	@Override
	public RedisManager getRedisManager() {
		return redisManager;
	}

	@Override
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;

		/**
		 * 初始化redisManager
		 */
		this.redisManager.init();
	}

	/**
	 * Returns the Redis session keys prefix.
	 * 
	 * @return The prefix
	 */
	@Override
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	@Override
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
}
