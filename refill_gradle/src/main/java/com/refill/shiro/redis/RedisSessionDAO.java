package com.refill.shiro.redis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RedisSessionDAO extends MemorySessionDAO {

	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

	//判断Redis服务器是否链接成功
	private boolean start = false;

	/**
	 * shiro-redis的session对象前缀
	 */
	private RedisManager redisManager;
	
	/**
	 * The Redis key prefix for the sessions 
	 */
	private String keyPrefix = "mayn:";
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		if(start){
			this.saveSession(session);
		}else{
			super.update(session);
		}

	}

	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}
		if(start){
			redisManager.del(this.getByteKey(session.getId()));
		}else{
			super.delete(session);
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		if(start) {
			Set<Session> sessions = new HashSet<>();
			Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
			if (keys != null && keys.size() > 0) {
				for (byte[] key : keys) {
					Object object = SerializeUtils.deserialize(redisManager.get(key));
					if (object != null && object instanceof SimpleSession) {
						sessions.add((Session) object);
					}
				}
			}
			return sessions;
		}else{
			return super.getActiveSessions();
		}
	}

	@Override
	protected Serializable doCreate(Session session) {
		if(start) {
			Serializable sessionId = this.generateSessionId(session);
			this.assignSessionId(session, sessionId);
			this.saveSession(session);
			return sessionId;
		}else {
			return super.doCreate(session);
		}
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			logger.error("session id is null");
			return null;
		}
		if(start){
			return (Session)SerializeUtils.deserialize(redisManager.get(this.getByteKey(sessionId)));
		}else {
			return super.doReadSession(sessionId);
		}
	}

	/**
	 * 通过sessionID获取session
	 * @param sessionId
	 * @return
     */
	public Session getSeesion(Serializable sessionId) {
		return doReadSession(sessionId);
	}

	/**
	 * save session
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException {
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}

		byte[] key = getByteKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);
		session.setTimeout(redisManager.getExpire()*1000);
		this.redisManager.set(key, value, redisManager.getExpire());
	}




	/**
	 * 获得byte[]型的key
	 * @param sessionId
	 * @return
	 */
	public byte[] getByteKey(Serializable sessionId){
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}



	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
		/**
		 * 初始化redisManager
		 */
		this.redisManager.init();
		this.start = this.redisManager.isStart();
	}

	/**
	 * Returns the Redis session keys
	 * prefix.
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key 
	 * prefix.
	 * @param keyPrefix The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	
	
}
