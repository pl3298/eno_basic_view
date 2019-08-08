package com.cbp.api.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Training implements Serializable{
	
//	private final String type;
//	private final String name;
//	private final String revision;
	
	public static final String TYPE = "A_Training";
	public static final String POLICY = "A_TrainingPolicy";
	private String type;
	private String name;
	private String revision;
	
	private String topic = null;
	private String duration = null;
	
	private Trainer trainer = null;
	
	public Training() {
		// TODO Auto-generated constructor stub
	}
	
	public Training(String type, String name, String revision) {
		this.type = type;
		this.name = name;
		this.revision = revision;
	}



	public Training(String type, String name, String revision, String topic, String duration) {
		this.type = type;
		this.name = name;
		this.revision = revision;
		this.topic = topic;
		this.duration = duration;
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

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Training [type=" + type + ", name=" + name + ", revision=" + revision + ", topic=" + topic
				+ ", duration=" + duration + "]";
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	
	

}
