package com.soft.application.DAO;

import java.util.List;

import com.soft.application.model.SculpturePOJO;

public interface SculptureDAO {

	//all this methods will be responsible for CRUD operations 
	public void saveSculpture(SculpturePOJO sculpture);
	public void updateSculpture(SculpturePOJO sculpture,int mtoid);
	public List<SculpturePOJO> getAllSculptures();
	public SculpturePOJO getSculptureById(int id);
	public void deleteSculpturePOJO(int id);
	public int getmtoidbyId(int id);
}
