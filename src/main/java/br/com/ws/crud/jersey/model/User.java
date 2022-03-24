package br.com.ws.crud.jersey.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class User {
	private int id;
    private String name;
    private String password;
    private Date data;
    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String password, Date data) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.data = data;
	}

	public User(String name, String password, Date data) {
		super();
		this.name = name;
		this.password = password;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	} 

	
}
