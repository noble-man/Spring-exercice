package be.main.Models;

public class Post {
	private String title;
	private String content;
	private String dt_redac;
	private String author;
	
	private String imageUrl;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDt_redac() {
		return dt_redac;
	}
	public void setDt_redac(String dt_redac) {
		this.dt_redac = dt_redac;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
