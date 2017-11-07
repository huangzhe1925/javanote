package com.hz.javanote.jackson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperTest {

	public static void main(String[] args) throws JsonProcessingException {
		List<User> users =new  ArrayList<User>();
		users.add(new User("roger", 1, true));
		users.add(new User("lucy", 2, false));
		User newUser=new User("lily",3,false);
		newUser.setUnderUser(new User("lily2",31,false));
		users.add(newUser);
		String values = new ObjectMapper().writeValueAsString(users);
		System.out.println(values);
	}
}

class User {

	private String name;
	private Integer id;
	private Boolean isMale;
	private User underUser;

	public User getUnderUser() {
		return underUser;
	}

	public void setUnderUser(User underUser) {
		this.underUser = underUser;
	}

	public User() {
		super();
	}

	public User(String name, Integer id, Boolean isMale) {
		super();
		this.name = name;
		this.id = id;
		this.isMale = isMale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsMale() {
		return isMale;
	}

	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}

}