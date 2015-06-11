package com.tmount.db.zdc.dto;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

public class NewDetailSub {
    private int id;
    private String title;
    private String author;
    private String description;
    private String content;
    private String pubDate;
    private String type;

    public NewDetailSub() {
	super();
    }

    public NewDetailSub(int id, String title, String author,
	    String description, String content, String pubDate, String type) {
	super();
	this.id = id;
	this.title = title;
	this.author = author;
	this.description = description;
	this.content = content;
	this.pubDate = pubDate;
	this.type = type;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getPubDate() {
	return pubDate;
    }

    public void setPubDate(String pubDate) {
	this.pubDate = pubDate;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

}
