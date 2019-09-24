package com.demo.zhulong.entity;

import java.util.Date;

public class User {
	
	//  ----------------------------------------------------------
	private Integer id;

	private String name;

	private String password;

	private String email;

	private Date registered_time;

	private Integer login_count;

	private String remark;

	private Integer status;

	private Integer premission;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Date getRegistered_time() {
		return registered_time;
	}

	public void setRegistered_time(Date registered_time) {
		this.registered_time = registered_time;
	}

	public Integer getLogin_count() {
		return login_count;
	}

	public void setLogin_count(Integer login_count) {
		this.login_count = login_count;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPremission() {
		return premission;
	}

	public void setPremission(Integer premission) {
		this.premission = premission;
	}




	//  ----------------------------------------------------------
   

}