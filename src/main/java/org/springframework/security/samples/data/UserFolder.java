package org.springframework.security.samples.data;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserFolder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	private Long folderId;
	
	private boolean canRead;
	
	private boolean canWrite;
	
	private Calendar created = Calendar.getInstance();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isCanRead() {
		return canRead;
	}

	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}

	public boolean isCanWrite() {
		return canWrite;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}
	
	
}
