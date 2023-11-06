/* (C)2023 */
package de.videoportal.video.entity;

import java.io.Serializable;

import de.videoportal.video.entity.impl.Thema;
import de.videoportal.video.entity.impl.Unterkategorie;

public class UnterkategorieTO implements Serializable {

	private static final long serialVersionUID = -3236541331415682968L;
	
	long id;
	String name;
	
	Thema thema;
	
	
	public UnterkategorieTO () {
		
	}


	public UnterkategorieTO(long id, String name, Thema thema) {
		super();
		this.id = id;
		this.name = name;
		this.thema = thema;
	}
	
	public UnterkategorieTO(long id, String name, long tid, String tname) {
		super();
		this.id = id;
		this.name = name;
		this.thema = new Thema(tid,tname);// Könnte man hier nicht einfach einen Konstruktor in der Klasse Thema nur mit ID anlegen ?
	}
	

	
	public Unterkategorie toUnterkategorie() {
		Unterkategorie unterkategorie = new Unterkategorie(
				this.getId(),
				this.getName(),
				this.getThema());
		return unterkategorie;
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


	public Thema getThema() {
		return thema;
	}


	public void setThema(Thema thema) {
		this.thema = thema;
	}

	
	
}
