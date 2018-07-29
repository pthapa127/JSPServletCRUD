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
import com.soft.application.DAO.DrawingDAO;
import com.soft.application.configuration.DatabaseConfiguration;
import com.soft.application.model.Drawing;
import com.soft.application.model.DrawingPOJO;

public class DrawingDAOImpl implements DrawingDAO{

	private Connection connection;
	private PreparedStatement pstat;
	private ResultSet resultSet;
	
	//this method will save drawings to the database
	@Override
	public void saveDrawing(DrawingPOJO drawing) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting database connection
		db.startTransaction();  //starting transaction
		Drawing dw=new Drawing(db);  //drawing matisse class that accepts db as parameter
		//setting properties to save in the database
		dw.setLotNumber(drawing.getLotNumber());
		dw.setArtistName(drawing.getArtistName());
		dw.setItemproducedDate(drawing.getItemProducedDate());
		dw.setClassification(drawing.getClassification());
		dw.setDescription(drawing.getDescription());
		dw.setAuctionDate(drawing.getAuctionDate());
		dw.setEstimated_price(drawing.getEstimated_price());
		dw.setDrawingMedium(drawing.getDrawingMedium());
		dw.setStatus(drawing.getStatus());
		dw.setLength(drawing.getLength());
		dw.setHeight(drawing.getHeight());
		dw.setSellStatus("auction");
		dw.setItemImage(drawing.getImage());  //image setting as a byte
		db.commit(); //commiting database
		db.close();  //closing database
	}


	//this method will get all the drawings from the database and return it in the form of list
	@Override
	public List<DrawingPOJO> getAllDrawings() {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting database connection
		db.startTransaction();  //starting transaction
		List<DrawingPOJO> drawings=new ArrayList<>();  //list to add all the drawings
		String cmd="SELECT REF(drawing) FROM Drawing drawing";  //query
		try{
			connection=db.getJDBCConnection(); //getting jdbc connection
			pstat=connection.prepareStatement(cmd);
			resultSet=pstat.executeQuery();  //executing query
			while(resultSet.next()){
				DrawingPOJO drawing=new DrawingPOJO();
				MtObject mtObj=(MtObject)resultSet.getObject(1);
				//setting properties
				Drawing d=new Drawing(db, mtObj.mtOid);
				drawing.setLotNumber(d.getLotNumber());
				drawing.setArtistName(d.getArtistName());
				drawing.setItemProducedDate(d.getItemproducedDate());
				drawing.setClassification(d.getClassification());
				drawing.setAuctionDate(d.getAuctionDate());
				drawing.setEstimated_price(d.getEstimated_price());
				drawing.setDrawingMedium(d.getDrawingMedium());
				drawing.setDescription(d.getDescription());
				drawing.setHeight(d.getHeight());
				drawing.setLength(d.getLength());
				drawing.setImage(d.getItemImage());
				byte[] image=d.getItemImage();  //getting image from database
				byte[] encodedImage=Base64.getEncoder().encode(image); //using Base64 to encode image from byte
				String encodedImg=new String(encodedImage);  //encoded image in the form of string
				drawing.setEncodedImage(encodedImg); //setting image to model class
				drawings.add(drawing); //adding all the drawing to list
			}
			db.commit(); //commiting database
			db.close(); //closing database
			return drawings;  //returning list of drawings
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	//this method will return drawing by id
	@Override
	public DrawingPOJO getDrawingById(int id) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //database connection
		DrawingPOJO drawing=new DrawingPOJO();  //model class
		db.startTransaction(); //staring transaction
		String cmd="SELECT REF(drawing) FROM Drawing drawing where drawing.lotNumber=?";
		connection=db.getJDBCConnection();  //getting jdbc connection
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			resultSet=pstat.executeQuery();
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1);
				Drawing d=new Drawing(db, mtObj.mtOid);  //matisse drawing class 
				//setting property to model class using matisse drawing class
				drawing.setLotNumber(d.getLotNumber());
				drawing.setArtistName(d.getArtistName());
				drawing.setItemProducedDate(d.getItemproducedDate());
				drawing.setClassification(d.getClassification());
				drawing.setAuctionDate(d.getAuctionDate());
				drawing.setEstimated_price(d.getEstimated_price());
				drawing.setDrawingMedium(d.getDrawingMedium());
				drawing.setDescription(d.getDescription());
				drawing.setHeight(d.getHeight());
				drawing.setLength(d.getLength());
				drawing.setImage(d.getItemImage());
			}
			db.commit(); //commiting database
			db.close();  //closing database
			return drawing;
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return null;
	}

	//this method will remove drawing
	@Override
	public void deleteDrawing(int id) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting db connection
		db.startTransaction(); //starting transaction
		String cmd="DELETE FROM Carving c where c.lotNumber=?"; //query
		connection=db.getJDBCConnection(); //getting jdbc connection
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			pstat.executeUpdate();  //executing remove drawing
			db.commit();  //commiting database
			db.close();  //closing database
		}catch(SQLException ex){
			ex.printStackTrace(); //printing stack trace
		}
		
	}

	//this method will update drawing
	@Override
	public void updateDrawing(DrawingPOJO drawing, int mtoid) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting database connection
		db.startTransaction();  //starting transaction
		Drawing dw=new Drawing(db,mtoid);  //drawing matisse class that accepts db and oid as parameter
		//setting properties to update in the database
		dw.setLotNumber(drawing.getLotNumber());
		dw.setArtistName(drawing.getArtistName());
		dw.setItemproducedDate(drawing.getItemProducedDate());
		dw.setClassification(drawing.getClassification());
		dw.setDescription(drawing.getDescription());
		dw.setAuctionDate(drawing.getAuctionDate());
		dw.setEstimated_price(drawing.getEstimated_price());
		dw.setDrawingMedium(drawing.getDrawingMedium());
		dw.setStatus(drawing.getStatus());
		dw.setLength(drawing.getLength());
		dw.setHeight(drawing.getHeight());
		dw.setSellStatus("auction");
		dw.setItemImage(drawing.getImage());  //image setting as a byte
		db.commit(); //commiting database
		db.close();  //closing database
		
	}
	
	//this method will return mtoid
	@Override
	public int getMtoidById(int id) {
		// TODO Auto-generated method stub
		int mtoid=0;
		MtDatabase db=DatabaseConfiguration.getConnection();  //database connection
		db.startTransaction(); //starting transaction
		String cmd="SELECT REF(drawing) FROM Drawing drawing where drawing.lotNumber=?"; //query
		connection=db.getJDBCConnection();  //getting jdbc connection
		try{
			pstat=connection.prepareStatement(cmd); //executing query using preparestatemnt
			pstat.setInt(1, id); //setting parameter id to the query
			resultSet=pstat.executeQuery(); //executing preapredstatement
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1); //getting mtobject
				mtoid=mtObj.mtOid; //getting mtoid from matisse database
			}
			db.commit();  //commiting database
			db.close();  //closing database
			return mtoid;  //returning mtoid
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return 0;
	}

}
