package com.soft.application.DAO;

import java.util.List;

import com.soft.application.model.CarvingPOJO;

public interface CarvingDAO {

	//this interface contains all the methods to operate CRUD operations related to carving
	
	public void saveCarving(CarvingPOJO carving);
	public void updateCarving(CarvingPOJO carving,int mt_oid);
	public List<CarvingPOJO> getAllCarvings();
	public CarvingPOJO getCarvingById(int id);
	public void deleteCarving(int id);
	public int getMtOidById(int id);
}
