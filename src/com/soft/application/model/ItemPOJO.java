package com.soft.application.model;

//Item model class as a parent class
public class ItemPOJO {

	private int lotNumber;
	private String artistName;
	private String itemProducedDate;
	private String classification;
	private String description;
	private String auctionDate;
	private String estimated_price;
	private byte[] image;
	private String encodedImage;
	
	public int getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(int lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getItemProducedDate() {
		return itemProducedDate;
	}
	public void setItemProducedDate(String itemProducedDate) {
		this.itemProducedDate = itemProducedDate;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuctionDate() {
		return auctionDate;
	}
	public void setAuctionDate(String auctionDate) {
		this.auctionDate = auctionDate;
	}
	public String getEstimated_price() {
		return estimated_price;
	}
	public void setEstimated_price(String estimated_price) {
		this.estimated_price = estimated_price;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getEncodedImage() {
		return encodedImage;
	}
	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}
	
	
	
}
