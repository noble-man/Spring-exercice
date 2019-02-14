package be.main.Models;

public class Photo{
	
	private int id;
	private int width;
	private int height;
	private String url;
	private String photographer;
	private String photographer_url;
	//private Map<String, String> src;
	private String src;
	
	public Photo(String url) {
		this.setUrl(url);
	}
	
	public Photo(int id, int width, int height, String url, String photographer, String photographer_url, String src) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.url = url;
		this.photographer = photographer;
		this.photographer_url = photographer_url;
		this.src = src;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhotographer() {
		return photographer;
	}
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}
	public String getPhotographer_url() {
		return photographer_url;
	}
	public void setPhotographer_url(String photographer_url) {
		this.photographer_url = photographer_url;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	

}
