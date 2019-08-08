package com.cbp.api.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Trainer implements Serializable{
	
//	private final String type;
//	private final String name;
//	private final String revision;
	
	public static final String TYPE = "A_Trainer";
	public static final String POLICY = "A_TrainerPolicy";
	private String type;
	private String name;
	private String revision;
	
	private String description = null;

	public Trainer() {
		super();
	}

	public Trainer(String type, String name, String revision) {
		super();
		this.type = type;
		this.name = name;
		this.revision = revision;
	}

	public Trainer(String type, String name, String revision, String description) {
		super();
		this.type = type;
		this.name = name;
		this.revision = revision;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Trainer [type=" + type + ", name=" + name + ", revision=" + revision + ", description=" + description
				+ "]";
	}

}
