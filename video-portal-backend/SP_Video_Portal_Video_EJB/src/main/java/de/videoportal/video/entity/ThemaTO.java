/* (C)2023 */
package de.videoportal.video.entity;

import java.io.Serializable;
import java.util.List;

import de.videoportal.video.entity.impl.Thema;
import de.videoportal.video.entity.impl.Unterkategorie;

public class ThemaTO implements Serializable {

	private static final long serialVersionUID = -4686845569436097883L;
	
	long id;
	String name;
	List<Unterkategorie> unterkategorien;
	
	
	public ThemaTO(long id, String name, List<Unterkategorie> unterkategorien) {
		super();
		this.id = id;
		this.name = name;
		this.unterkategorien = unterkategorien;
	}
	
	
	public Thema toThema() {
		
		Thema thema = new Thema(
				this.getId(),
				this.getName(),
				this.getUnterkategorien());
		return thema;
	}
		
		public ThemaTO() {
			
		}

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


	public List<Unterkategorie> getUnterkategorien() {
		return unterkategorien;
	}


	public void setUnterkategorien(List<Unterkategorie> unterkategorien) {
		this.unterkategorien = unterkategorien;
	}
	

}
