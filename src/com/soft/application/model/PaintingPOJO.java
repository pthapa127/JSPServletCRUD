package com.soft.application.model;

//painting model class
public class PaintingPOJO extends ItemPOJO{
	private String paintingMedium;
	private String status;
	private double height;
	private double length;
	public String getPaintingMedium() {
		return paintingMedium;
	}
	public void setPaintingMedium(String paintingMedium) {
		this.paintingMedium = paintingMedium;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
