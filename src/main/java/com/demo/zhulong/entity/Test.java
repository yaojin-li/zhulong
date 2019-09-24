package com.demo.zhulong.entity;

import java.util.Date;

public class Test {

//  ----------------------------------------------------------
private Integer id;

	private String name;

	private String first_actor;

	private String first_actress;

	private Date release_time;

	private String director;

	private Double box_office;

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

	public String getFirst_actor() {
		return first_actor;
	}

	public void setFirst_actor(String first_actor) {
		this.first_actor = first_actor == null ? null : first_actor.trim();
	}

	public String getFirst_actress() {
		return first_actress;
	}

	public void setFirst_actress(String first_actress) {
		this.first_actress = first_actress == null ? null : first_actress.trim();
	}

	public Date getRelease_time() {
		return release_time;
	}

	public void setRelease_time(Date release_time) {
		this.release_time = release_time;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director == null ? null : director.trim();
	}

	public Double getBox_office() {
		return box_office;
	}

	public void setBox_office(Double box_office) {
		this.box_office = box_office;
	}

	//  ----------------------------------------------------------
	

	


}