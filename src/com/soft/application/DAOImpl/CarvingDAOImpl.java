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
import com.soft.application.DAO.CarvingDAO;
import com.soft.application.configuration.DatabaseConfiguration;
import com.soft.application.model.Carving;
import com.soft.application.model.CarvingPOJO;

public class CarvingDAOImpl implements CarvingDAO{

	private Connection connection;  //connection sql 
	private PreparedStatement pstat; //prepared statement sql
	private ResultSet resultSet; //resultset sql
	
	//method to save carving to database
	@Override
	public void saveCarving(CarvingPOJO carving) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection from DatabaseConfiguration class
		db.startTransaction(); //starting transaction
		Carving c=new Carving(db);  //making object of carving class
		//setting property to carving class 
		c.setLotNumber(carving.getLotNumber()); 
		c.setArtistName(carving.getArtistName());
		c.setItemproducedDate(carving.getItemProducedDate());
		c.setClassification(carving.getClassification());
		c.setDescription(carving.getDescription());
		c.setAuctionDate(carving.getAuctionDate());
		c.setEstimated_price(carving.getEstimated_price());
		c.setMaterialUsed(carving.getMaterialUsed());
		c.setHeight(carving.getHeight());
		c.setLength(carving.getLength());
		c.setWidth(carving.getWidth());
		c.setWeight(carving.getWeight());
		c.setSellStatus("auction");
		c.setItemImage(carving.getImage());
		db.commit();  //committing database
		db.close(); //closing database
	}

	
	//method to list all the carvings from the database
	@Override
	public List<CarvingPOJO> getAllCarvings() {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting database connection
		db.startTransaction();  //starting transaction
		List<CarvingPOJO> carvings=new ArrayList<>();//list to store all the carvings from the database
		String cmd="SELECT REF(carving) FROM Carving carving";  //query that will refer carving object and will return Mtobject
		
		try{
			connection=db.getJDBCConnection();  //getting jdbc connection using matisse database
			pstat=connection.prepareStatement(cmd);  //executing prepared statement
			resultSet=pstat.executeQuery();  //executing query and storing in resultset
			while(resultSet.next()){
				CarvingPOJO c=new CarvingPOJO(); //new carving object to bind all the values 
				MtObject mtobj=(MtObject)resultSet.getObject(1);  //getting mtobject using resultset
				Carving cc=new Carving(db, mtobj.mtOid); //matisse carving class that will contain all the values
				//setting values from matisse carving class to CarvingPOJO class
				c.setLotNumber(cc.getLotNumber()); 
				c.setArtistName(cc.getArtistName());
				c.setItemProducedDate(cc.getItemproducedDate());
				c.setClassification(cc.getClassification());
				c.setAuctionDate(cc.getAuctionDate());
				c.setEstimated_price(cc.getEstimated_price());
				c.setMaterialUsed(cc.getMaterialUsed());
				c.setDescription(cc.getDescription());
				c.setLength(cc.getLength());
				c.setHeight(cc.getHeight());
				c.setWeight(cc.getWeight());
				c.setImage(cc.getItemImage());
				byte[] imageByte=cc.getItemImage(); //getting image as byte value from database
				byte[] encoded=Base64.getEncoder().encode(imageByte); //enoding image byte value using Base64
				String encodedImage=new String(encoded);  //encoded image as string from byte 
				c.setEncodedImage(encodedImage);  //setting encoded image
				carvings.add(c);  //adding to list
			}
			db.commit(); //committing database
			db.close();//closing connection
			return carvings;  //returning list of carvings
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return null;
	}

	//method to get particular carving by id/lotNumber
	@Override
	public CarvingPOJO getCarvingById(int id) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();//getting database connection from database configuration class
		CarvingPOJO carving=new CarvingPOJO(); //declaring carving class object
		db.startTransaction(); //starting transaction
		String cmd="SELECT REF(c) FROM Carving c where c.lotNumber=?"; //query that refer carving object
		connection=db.getJDBCConnection(); //getting jdbc connection
		try{
			pstat=connection.prepareStatement(cmd);//executing prepare statement
			pstat.setInt(1, id);
			resultSet=pstat.executeQuery(); //executing query
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1); //getting MtObject from result set from position 1
				Carving c=new Carving(db, mtObj.mtOid); //matisse carving class 
				//setting all the values from matisse carving class to carving class model
				carving.setLotNumber(c.getLotNumber());
				carving.setArtistName(c.getArtistName());
				carving.setItemProducedDate(c.getItemproducedDate());
				carving.setClassification(c.getClassification());
				carving.setAuctionDate(c.getAuctionDate());
				carving.setEstimated_price(c.getEstimated_price());
				carving.setMaterialUsed(c.getMaterialUsed());
				carving.setDescription(c.getDescription());
				carving.setHeight(c.getHeight());
				carving.setLength(c.getLength());
				carving.setWidth(c.getWidth());
				carving.setWeight(c.getWeight());
				carving.setImage(c.getItemImage());
			}
			db.commit(); //committing database
			db.close();  //closing database
			return carving;  //returning carving by id
		}catch(SQLException ex){
			ex.printStackTrace(); //printing stack trace if exception occurs
		}
		
		return null;
	}

	//deleting carving using id/lotNumber
	@Override
	public void deleteCarving(int id) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting db connection 
		db.startTransaction(); //starting transaction
		String cmd="DELETE FROM Carving c where c.lotNumber=?";
		connection=db.getJDBCConnection();  //getting jdbc connection
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			pstat.executeUpdate();//executing delete 
			db.commit();
			db.close();
		}catch(SQLException ex){
			ex.printStackTrace(); //printing stack track if exception occurs
		}
	}

	//method to update carving according to mtoid
	@Override
	public void updateCarving(CarvingPOJO carving, int mt_oid) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection from DatabaseConfiguration class
		db.startTransaction(); //starting transaction
		Carving c=new Carving(db,mt_oid);  //making object of carving class to update according to oid
		//setting property to carving class 
		c.setLotNumber(carving.getLotNumber()); 
		c.setArtistName(carving.getArtistName());
		c.setItemproducedDate(carving.getItemProducedDate());
		c.setClassification(carving.getClassification());
		c.setDescription(carving.getDescription());
		c.setAuctionDate(carving.getAuctionDate());
		c.setEstimated_price(carving.getEstimated_price());
		c.setMaterialUsed(carving.getMaterialUsed());
		c.setHeight(carving.getHeight());
		c.setLength(carving.getLength());
		c.setWidth(carving.getWidth());
		c.setWeight(carving.getWeight());
		c.setItemImage(carving.getImage());
		c.setSellStatus("auction");
		db.commit();  //committing database
		db.close(); //closing database
	}

	//getting mtoid from the matisse database using id/lotNumber
	@Override
	public int getMtOidById(int id) {
		// TODO Auto-generated method stub
		int mtOid=0;
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting db connection
		db.startTransaction(); //starting transaction
		String cmd="SELECT REF(c) FROM Carving c where c.lotNumber=?"; //select query 
		connection=db.getJDBCConnection();  //getting jdbc connection
		
		try{
			pstat=connection.prepareStatement(cmd);  //executing prepared statememt 
			pstat.setInt(1, id);  //setting parameter as id
			resultSet=pstat.executeQuery(); //executing prepared statement
			while(resultSet.next()){  
				MtObject mtObj=(MtObject) resultSet.getObject(1);  //getting mtobject using resultset
				mtOid=mtObj.mtOid;  //getting mtoid
			}
			db.commit(); //commiting database
			db.close();  //closing database
			return mtOid;  //returning mtoid
		}catch(SQLException ex){
			ex.printStackTrace(); //printing stack trace if exception occurs
		}
		return 0;
	}

}
