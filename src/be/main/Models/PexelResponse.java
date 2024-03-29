package be.main.Models;

import java.util.ArrayList;
import java.util.Map;

public class PexelResponse {

	private int total_results;
	private int page;
	private int per_page;
	private ArrayList<Photo> photos;
	private String next_page;
	
	public int getTotal_results() {
		return total_results;
	}
	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPer_page() {
		return per_page;
	}
	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}
	public ArrayList getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList photos) {
		this.photos = photos;
	}
	public String getNext_page() {
		return next_page;
	}
	public void setNext_page(String next_page) {
		this.next_page = next_page;
	}

	
}
