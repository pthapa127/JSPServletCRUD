package com.soft.application.DAO;

import java.util.List;

import com.soft.application.model.PaintingPOJO;

public interface PaintingDAO {

	public void savePainting(PaintingPOJO painting);
	public void updatePainting(PaintingPOJO painting,int mt_oid);
	public List<PaintingPOJO> getAllPaintings();
	public PaintingPOJO getPaintingById(int id);
	public void deletePainting(int id);
	public int getMtoidById(int id);
}
