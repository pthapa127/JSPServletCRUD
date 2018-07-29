package com.soft.application.model;

//drawing model class
public class DrawingPOJO extends ItemPOJO{

	private String drawingMedium;
	private String status;
	private double height;
	private double length;
	public String getDrawingMedium() {
		return drawingMedium;
	}
	public void setDrawingMedium(String drawingMedium) {
		this.drawingMedium = drawingMedium;
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
