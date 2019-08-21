package com.etc.news.entity;

public class News {
	private Integer news_id;
	private Type news_type;  //外键使用对象类型
	private User news_author;  //外键使用对象类型
    private String news_title;
    private String news_content;
    private String news_time;
    private String news_image;
	public Integer getNews_id() {
		return news_id;
	}
	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}
	public Type getNews_type() {
		return news_type;
	}
	public void setNews_type(Type news_type) {
		this.news_type = news_type;
	}
	public User getNews_author() {
		return news_author;
	}
	public void setNews_author(User news_author) {
		this.news_author = news_author;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public String getNews_time() {
		return news_time;
	}
	public void setNews_time(String news_time) {
		this.news_time = news_time;
	}
	public String getNews_image() {
		return news_image;
	}
	public void setNews_image(String news_image) {
		this.news_image = news_image;
	}
    
    
}
