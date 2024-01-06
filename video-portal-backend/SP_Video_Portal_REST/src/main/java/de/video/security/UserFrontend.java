package de.video.security;

public class UserFrontend {

	private String name;
	private int rolle;
	
	public UserFrontend() {
	}
	
	public UserFrontend(String name, int rolle) {
		super();
		this.name = name;
		this.rolle = rolle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRolle() {
		return rolle;
	}

	public void setRolle(int rolle) {
		this.rolle = rolle;
	}
	
	
	
}
