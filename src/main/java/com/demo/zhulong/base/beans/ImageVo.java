package com.demo.zhulong.base.beans;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "image")
public class ImageVo {
	//  ----------------------------------------------------------

    @Id
	private Long id;

	private String title;

	private String new_title;

	private Date create_time;

	private Date modify_time;

	private String type;

	private Integer size;

	private String position;

	private String img_UUID;

	private String user;

	private String description;

	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getNew_title() {
		return new_title;
	}

	public void setNew_title(String new_title) {
		this.new_title = new_title == null ? null : new_title.trim();
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	public String getImg_UUID() {
		return img_UUID;
	}

	public void setImg_UUID(String img_UUID) {
		this.img_UUID = img_UUID == null ? null : img_UUID.trim();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user == null ? null : user.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
	//  ----------------------------------------------------------

    



}