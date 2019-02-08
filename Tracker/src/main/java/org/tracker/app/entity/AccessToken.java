package org.tracker.app.entity;

import org.springframework.data.annotation.Id;

public class AccessToken {
	@Id
	private String tokenId;
	private String username;
	private boolean activeSession = false;
	private long created;
	private long lastAccessed;

	public long getCreated() {
		return created;
	}

	public long getLastAccessed() {
		return lastAccessed;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public String getTokenId() {
		return tokenId;
	}

	public String getUsername() {
		return username;
	}

	public boolean isActiveSession() {
		return activeSession;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setActiveSession(boolean activeSession) {
		this.activeSession = activeSession;
	}

}
