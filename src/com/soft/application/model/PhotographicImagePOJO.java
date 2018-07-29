package com.soft.application.model;

//photographic model class
public class PhotographicImagePOJO extends ItemPOJO{

	private String imageType;
	private double height;
	private double length;
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	
	
}
