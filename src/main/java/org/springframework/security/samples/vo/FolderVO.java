package org.springframework.security.samples.vo;

import java.util.ArrayList;
import java.util.List;

public class FolderVO {
	private long id;
	private String name;
	private boolean type;
	private String path;
	private boolean read;
	private boolean write;
	private Long parentId;
	private List<FolderVO> children;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<FolderVO> getChildren() {
		if(children==null)
			children = new ArrayList();
		return children;
	}
	public void setChildren(List<FolderVO> children) {
		this.children = children;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public boolean isWrite() {
		return write;
	}
	public void setWrite(boolean write) {
		this.write = write;
	}
}
