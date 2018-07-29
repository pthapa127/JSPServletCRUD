package com.soft.application.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.matisse.MtDatabase;
import com.matisse.reflect.MtObject;
import com.soft.application.DAO.PhotographicImageDAO;
import com.soft.application.configuration.DatabaseConfiguration;
import com.soft.application.model.PhotographicImage;
import com.soft.application.model.PhotographicImagePOJO;

public class PhotographicImageDAOImpl implements PhotographicImageDAO{

	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement pstat;
	
	//method to save photographic image
	@Override
	public void savePhotographicImage(PhotographicImagePOJO pimage) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection
		db.startTransaction();  //starting transaction
		PhotographicImage pi=new PhotographicImage(db);  
		//setting properties from using model class and matisse db class
		pi.setLotNumber(pimage.getLotNumber());
		pi.setArtistName(pimage.getArtistName());
		pi.setItemproducedDate(pimage.getItemProducedDate());
		pi.setClassification(pimage.getClassification());
		pi.setDescription(pimage.getDescription());
		pi.setAuctionDate(pimage.getAuctionDate());
		pi.setEstimated_price(pimage.getEstimated_price());
		pi.setImageType(pimage.getImageType());
		pi.setLength(pimage.getLength());
		pi.setHeight(pimage.getHeight());
		pi.setSellStatus("auction");
		pi.setItemImage(pimage.getImage());  //setting image as byte
		db.commit();  //committing database
		db.close(); //closing database
	}


	//method to list all the photographic image and return as a list
	@Override
	public List<PhotographicImagePOJO> getAllPhotographicImages() {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();
		db.startTransaction();
		List<PhotographicImagePOJO> pis=new ArrayList<>();
		String cmd="SELECT REF(photographicImage) FROM PhotographicImage photographicImage";
		try{
			connection=db.getJDBCConnection();
			pstat=connection.prepareStatement(cmd);
			resultSet=pstat.executeQuery();
			while(resultSet.next()){
				PhotographicImagePOJO pi=new PhotographicImagePOJO();
				MtObject mtObj=(MtObject)resultSet.getObject(1);
				PhotographicImage pp=new PhotographicImage(db, mtObj.mtOid);
				pi.setLotNumber(pp.getLotNumber());
				pi.setArtistName(pp.getArtistName());
				pi.setItemProducedDate(pp.getItemproducedDate());
				pi.setClassification(pp.getClassification());
				pi.setAuctionDate(pp.getAuctionDate());
				pi.setEstimated_price(pp.getEstimated_price());
				pi.setImageType(pp.getImageType());
				pi.setDescription(pp.getDescription());
				pi.setLength(pp.getLength());
				pi.setHeight(pp.getHeight());
				byte[] image=pp.getItemImage();  //getting image as byte from database
				byte[] imageenco=Base64.getEncoder().encode(image); //using Base64 to encode image
				String imageEncode=new String(imageenco); 
				pi.setEncodedImage(imageEncode);  //setting image
				pis.add(pi);
			}
			db.commit();
			db.close();
			return pis;
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	//method to get single photographic image by lotNumber/id
	@Override
	public PhotographicImagePOJO getPhotographicImageById(int id) {
		// TODO Auto-generated method stub
		PhotographicImagePOJO pi=new PhotographicImagePOJO();
		MtDatabase db=DatabaseConfiguration.getConnection();//db connection
		db.startTransaction(); //starting transaction
		String cmd="SELECT REF(pimage) FROM PhotographicImage pimage where pimage.lotNumber=?";
		connection=db.getJDBCConnection();
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			resultSet=pstat.executeQuery();  //query execute
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1); //getting mtobject from database
				PhotographicImage pp=new PhotographicImage(db, mtObj.mtOid); //matisse class 
				//setting values
				pi.setLotNumber(pp.getLotNumber());
				pi.setArtistName(pp.getArtistName());
				pi.setItemProducedDate(pp.getItemproducedDate());
				pi.setClassification(pp.getClassification());
				pi.setAuctionDate(pp.getAuctionDate());
				pi.setEstimated_price(pp.getEstimated_price());
				pi.setImageType(pp.getImageType());
				pi.setDescription(pp.getDescription());
				pi.setLength(pp.getLength());
				pi.setHeight(pp.getHeight());
				pi.setImage(pp.getItemImage());
			}
			db.commit(); //committing database
			db.close(); //closing database
			return pi; //returning photographicimage as object
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	//method to remove photographic image
	@Override
	public void deletePhotographicImage(int id) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection
		db.startTransaction();  //starting transaction
		String cmd="DELETE FROM PhotographicImage pi where pi.lotNumber=?"; //delete query
		connection=db.getJDBCConnection();  //getting jdbc connection
		try{
			pstat=connection.prepareStatement(cmd); 
			pstat.setInt(1, id);
			pstat.executeQuery();  //executing query
			db.commit();  //committing database
			db.close();  //closing database
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}

	//method to update photographic image using oid
	@Override
	public void updatePhotographicImage(PhotographicImagePOJO pimage, int mtoid) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection
		db.startTransaction();  //starting transaction
		PhotographicImage pi=new PhotographicImage(db,mtoid);  
		//setting properties from using model class and matisse db class
		pi.setLotNumber(pimage.getLotNumber());
		pi.setArtistName(pimage.getArtistName());
		pi.setItemproducedDate(pimage.getItemProducedDate());
		pi.setClassification(pimage.getClassification());
		pi.setDescription(pimage.getDescription());
		pi.setAuctionDate(pimage.getAuctionDate());
		pi.setEstimated_price(pimage.getEstimated_price());
		pi.setImageType(pimage.getImageType());
		pi.setLength(pimage.getLength());
		pi.setHeight(pimage.getHeight());
		pi.setSellStatus("auction");
		pi.setItemImage(pimage.getImage());  //setting image as byte
		db.commit();  //committing database
		db.close(); //closing database
	}

	//method to get mtoid from database to update photographic image
	@Override
	public int getmtoidById(int id) {
		// TODO Auto-generated method stub
		int mtoid=0;
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting db connection
		db.startTransaction(); //starting transaction
		String cmd="SELECT REF(pimage) FROM PhotographicImage pimage where pimage.lotNumber=?"; //query
		connection=db.getJDBCConnection(); //getting jdbc connection
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			resultSet=pstat.executeQuery();  //executing query
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1);  //getting mtobject from database
				mtoid=mtObj.mtOid;  //getting oid
			}
			db.commit(); //commiting database
			db.close(); //closing database
			return mtoid;  //returning mtoid
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return 0;
	}

}
