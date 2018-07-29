package com.soft.application.DAO;

import java.util.List;

import com.soft.application.model.DrawingPOJO;

public interface DrawingDAO {

	
	//this interface contains all the CRUD logic methods 
	public void saveDrawing(DrawingPOJO drawing);
	public void updateDrawing(DrawingPOJO drawing,int mtoid);
	public List<DrawingPOJO> getAllDrawings();
	public DrawingPOJO getDrawingById(int id);
	public void deleteDrawing(int id);
	public int getMtoidById(int id);
}
