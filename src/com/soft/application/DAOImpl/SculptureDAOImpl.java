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
import com.soft.application.DAO.SculptureDAO;
import com.soft.application.configuration.DatabaseConfiguration;
import com.soft.application.model.Sculpture;
import com.soft.application.model.SculpturePOJO;

public class SculptureDAOImpl implements SculptureDAO{

	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement pstat;
	
	//method to save sculpture to database
	@Override
	public void saveSculpture(SculpturePOJO sculpture) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting database connection
		db.startTransaction(); //starting transaction
		Sculpture s=new Sculpture(db);  //matisse sculpture class
		//setting all the values to the matisse sculpture class
		s.setLotNumber(sculpture.getLotNumber());
		s.setArtistName(sculpture.getArtistName());
		s.setItemproducedDate(sculpture.getItemProducedDate());
		s.setClassification(sculpture.getClassification());
		s.setDescription(sculpture.getDescription());
		s.setAuctionDate(sculpture.getAuctionDate());
		s.setEstimated_price(sculpture.getEstimated_price());
		s.setMaterialUsed(sculpture.getMaterialUsed());
		s.setHeight(sculpture.getHeight());
		s.setWidth(sculpture.getWidth());
		s.setLength(sculpture.getLength());
		s.setWeight(sculpture.getWeight());
		s.setSellStatus("auction");
		s.setItemImage(sculpture.getImage());  //setting image as a byte
		db.commit(); //commiting database
		db.close(); //closing database
	}


	//method to get all the sculptures from database
	@Override
	public List<SculpturePOJO> getAllSculptures() {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection
		db.startTransaction();  //starting transaction
		List<SculpturePOJO> sculptures=new ArrayList<>(); //list to store all the sculpture from the database
		String cmd="SELECT REF(sculpture) FROM Sculpture sculpture"; //query to get select all the sculptures from the database
		try{
			connection=db.getJDBCConnection(); //getting jdbc connection
			pstat=connection.prepareStatement(cmd);
			resultSet=pstat.executeQuery();  //executing query
			while(resultSet.next()){
				SculpturePOJO sculpture=new SculpturePOJO();
				MtObject mtObj=(MtObject)resultSet.getObject(1); //getting mtobject from database
				Sculpture s=new Sculpture(db, mtObj.mtOid);
				//setting values from database to properties if model class
				sculpture.setLotNumber(s.getLotNumber());
				sculpture.setArtistName(s.getArtistName());
				sculpture.setItemProducedDate(s.getItemproducedDate());
				sculpture.setClassification(s.getClassification());
				sculpture.setAuctionDate(s.getAuctionDate());
				sculpture.setEstimated_price(s.getEstimated_price());
				sculpture.setMaterialUsed(s.getMaterialUsed());
				sculpture.setDescription(s.getDescription());
				sculpture.setLength(s.getLength());
				sculpture.setHeight(s.getHeight());
				sculpture.setWeight(s.getWeight());
				sculpture.setWidth(s.getWidth());
				byte[] image=s.getItemImage(); //getting image as byte from database
				byte[] encodeImage=Base64.getEncoder().encode(image); //encoding image using Base64
				String imageencoder=new String(encodeImage);
				sculpture.setEncodedImage(imageencoder); //setting image 
				sculptures.add(sculpture);  //adding all the properties with value to database
			}
			db.commit();  //commiting database
			db.close(); //closing database
			return sculptures;  //returning list of sculptures
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	//method to get sculpture by id
	@Override
	public SculpturePOJO getSculptureById(int id) {
		// TODO Auto-generated method stub
		SculpturePOJO sculpture=new SculpturePOJO();
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting connection
		db.startTransaction(); //starting transaction
		String cmd="SELECT REF(sculpture) FROM Sculpture sculpture where sculpture.lotNumber=?";
		connection=db.getJDBCConnection();
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			resultSet=pstat.executeQuery();  //executing query
			while(resultSet.next()){
				MtObject mtObj=(MtObject)resultSet.getObject(1); //getting mtobject from database
				Sculpture s=new Sculpture(db, mtObj.mtOid);
				//setting values from database to properties if model class
				sculpture.setLotNumber(s.getLotNumber());
				sculpture.setArtistName(s.getArtistName());
				sculpture.setItemProducedDate(s.getItemproducedDate());
				sculpture.setClassification(s.getClassification());
				sculpture.setAuctionDate(s.getAuctionDate());
				sculpture.setEstimated_price(s.getEstimated_price());
				sculpture.setMaterialUsed(s.getMaterialUsed());
				sculpture.setDescription(s.getDescription());
				sculpture.setLength(s.getLength());
				sculpture.setHeight(s.getHeight());
				sculpture.setWidth(s.getWidth());
				sculpture.setWeight(s.getWeight());
				sculpture.setImage(s.getItemImage());
				
			}
			db.commit();  //committing database
			db.close();  //closing database
			return sculpture; //returning single sculpture model 
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	//method to remove sculpture
	@Override
	public void deleteSculpturePOJO(int id) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection
		db.startTransaction(); //starting transaction
		String cmd="DELETE FROM Sculpture sculpture where sculpture.lotNumber=?"; //delete query
		connection=db.getJDBCConnection();  //getting jdbc connection
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			pstat.executeUpdate();  //executing query
			db.commit(); //committing database
			db.close(); //closing database
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	//method to update sculpture
	@Override
	public void updateSculpture(SculpturePOJO sculpture, int mtoid) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting database connection
		db.startTransaction(); //starting transaction
		Sculpture s=new Sculpture(db,mtoid);  //matisse sculpture class
		//setting all the values to the matisse sculpture class
		s.setLotNumber(sculpture.getLotNumber());
		s.setArtistName(sculpture.getArtistName());
		s.setItemproducedDate(sculpture.getItemProducedDate());
		s.setClassification(sculpture.getClassification());
		s.setDescription(sculpture.getDescription());
		s.setAuctionDate(sculpture.getAuctionDate());
		s.setEstimated_price(sculpture.getEstimated_price());
		s.setMaterialUsed(sculpture.getMaterialUsed());
		s.setHeight(sculpture.getHeight());
		s.setWidth(sculpture.getWidth());
		s.setLength(sculpture.getLength());
		s.setWeight(sculpture.getWeight());
		s.setSellStatus("auction");
		s.setItemImage(sculpture.getImage());  //setting image as a byte
		db.commit(); //commiting database
		db.close(); //closing database
	}

	//method to get mtoid from database
	@Override
	public int getmtoidbyId(int id) {
		// TODO Auto-generated method stub
		int mtoid=0;
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting connection
		db.startTransaction(); //starting transaction
		String cmd="SELECT REF(sculpture) FROM Sculpture sculpture where sculpture.lotNumber=?"; //query
		connection=db.getJDBCConnection(); //getting JDBC connection
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			resultSet=pstat.executeQuery();  //executing query
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1); //getting mtobject 
				mtoid=mtObj.mtOid; //getting mtoid
			}
			db.commit(); //commiting database
			db.close(); //closing database
			return mtoid; //returning mtoid
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return 0;
	}

}
