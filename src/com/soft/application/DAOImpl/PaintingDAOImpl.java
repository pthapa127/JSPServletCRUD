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
import com.soft.application.DAO.PaintingDAO;
import com.soft.application.configuration.DatabaseConfiguration;
import com.soft.application.model.Painting;
import com.soft.application.model.PaintingPOJO;


public class PaintingDAOImpl implements PaintingDAO{

	
	private Connection connection;
	private PreparedStatement pstat;
	private ResultSet resultSet;
	
	@Override
	public void savePainting(PaintingPOJO painting) {
		// TODO Auto-generated method stub
		
		
		MtDatabase db=DatabaseConfiguration.getConnection();
		db.startTransaction();
		Painting p=new Painting(db);
		p.setLotNumber(painting.getLotNumber());
		p.setArtistName(painting.getArtistName());
		p.setItemproducedDate(painting.getItemProducedDate());
		p.setClassification(painting.getClassification());
		p.setDescription(painting.getDescription());
		p.setAuctionDate(painting.getAuctionDate());
		p.setEstimated_price(painting.getEstimated_price());
		p.setPaintingMedium(painting.getPaintingMedium());
		p.setStatus(painting.getStatus());
		p.setLength(painting.getLength());
		p.setHeight(painting.getHeight());
		p.setItemImage(painting.getImage());
		p.setSellStatus("auction");
		db.commit();
		db.close();
		
	}

	@Override
	public void updatePainting(PaintingPOJO painting,int mt_oid) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();
		db.startTransaction();
		Painting p=new Painting(db, mt_oid);
		p.setLotNumber(painting.getLotNumber());
		p.setArtistName(painting.getArtistName());
		p.setItemproducedDate(painting.getItemProducedDate());
		p.setClassification(painting.getClassification());
		p.setDescription(painting.getDescription());
		p.setAuctionDate(painting.getAuctionDate());
		p.setEstimated_price(painting.getEstimated_price());
		p.setPaintingMedium(painting.getPaintingMedium());
		p.setStatus(painting.getStatus());
		p.setLength(painting.getLength());
		p.setHeight(painting.getHeight());
		p.setItemImage(painting.getImage());
		p.setSellStatus("auction");
		db.commit();
		db.close();	
	}

	@Override
	public List<PaintingPOJO> getAllPaintings() {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();
		db.startTransaction();
		List<PaintingPOJO> paintings=new ArrayList<>();
		String cmd="SELECT REF(painting) FROM Painting painting";
		
		try{
			connection=db.getJDBCConnection();
			pstat=connection.prepareStatement(cmd);
			resultSet=pstat.executeQuery();
			while(resultSet.next()){
				PaintingPOJO painting=new PaintingPOJO();
				MtObject mtObj=(MtObject)resultSet.getObject(1);
				Painting p=new Painting(db,mtObj.mtOid);
				painting.setLotNumber(p.getLotNumber());
				painting.setArtistName(p.getArtistName());
				painting.setItemProducedDate(p.getItemproducedDate());
				painting.setClassification(p.getClassification());
				painting.setAuctionDate(p.getAuctionDate());
				painting.setEstimated_price(p.getEstimated_price());
				painting.setPaintingMedium(p.getPaintingMedium());
				painting.setDescription(p.getDescription());
				painting.setHeight(p.getHeight());
				painting.setLength(p.getLength());
				painting.setImage(p.getItemImage());
				byte[] imageByte=p.getItemImage();
				byte[]encoded=Base64.getEncoder().encode(imageByte);
				String encodedStringImage=new String(encoded);
				painting.setEncodedImage(encodedStringImage);
				paintings.add(painting);
			}
			db.commit();
			db.close();
			return paintings;
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public PaintingPOJO getPaintingById(int id) {
		// TODO Auto-generated method stub
		String cmd="SELECT REF(painting) FROM Painting painting where painting.lotNumber=?";
		MtDatabase db=DatabaseConfiguration.getConnection();
		PaintingPOJO painting=new PaintingPOJO();
		db.startTransaction();
		connection=db.getJDBCConnection();
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			resultSet=pstat.executeQuery();
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1);
				Painting p=new Painting(db, mtObj.mtOid);
				painting.setLotNumber(p.getLotNumber());
				painting.setArtistName(p.getArtistName());
				painting.setItemProducedDate(p.getItemproducedDate());
				painting.setClassification(p.getClassification());
				painting.setDescription(p.getDescription());
				painting.setAuctionDate(p.getAuctionDate());
				painting.setEstimated_price(p.getEstimated_price());
				painting.setPaintingMedium(p.getPaintingMedium());
				painting.setStatus(p.getStatus());
				painting.setHeight(p.getHeight());
				painting.setLength(p.getLength());
				painting.setImage(p.getItemImage());
			}
			db.commit();
			db.close();
			return painting;
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void deletePainting(int id) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();
		db.startTransaction();
		String cmd="DELETE FROM Painting painting where painting.lotNumber=?";
		connection=db.getJDBCConnection();
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			pstat.executeUpdate();
			db.commit();
			db.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	@Override
	public int getMtoidById(int id) {
		// TODO Auto-generated method stub
		int mt_oid=0;
		MtDatabase db=DatabaseConfiguration.getConnection();
		db.startTransaction();
		String cmd="SELECT REF(p) FROM Painting p where p.lotNumber=?";
		connection=db.getJDBCConnection();
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, id);
			resultSet=pstat.executeQuery();
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1);
				mt_oid=mtObj.mtOid;
			}
			db.commit();
			db.close();
			return mt_oid; 
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return 0;
	}

}
