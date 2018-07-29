package com.soft.application.DAO;

import java.util.List;

import com.soft.application.model.PhotographicImagePOJO;

public interface PhotographicImageDAO {

	//this interface contains all the CRUD method logic for photographic image class
	public void savePhotographicImage(PhotographicImagePOJO pimage);
	public void updatePhotographicImage(PhotographicImagePOJO pimage,int mtoid);
	public List<PhotographicImagePOJO> getAllPhotographicImages();
	public PhotographicImagePOJO getPhotographicImageById(int id);
	public void deletePhotographicImage(int id);
	public int getmtoidById(int id);
}
