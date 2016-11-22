package com.babbla.models;

public class Chat {
	private String link;
	private String name;

	public Chat(String name, String link) {
		this.link = link;
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}
}
