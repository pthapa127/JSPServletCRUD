package com.soft.application.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.soft.application.DAOImpl.PaintingDAOImpl;
import com.soft.application.model.PaintingPOJO;

/**
 * Servlet implementation class PaintingController
 */

public class PaintingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private PaintingPOJO painting;
	private PaintingDAOImpl paintingDAO;
    public PaintingController() {
        super();
        // TODO Auto-generated constructor stub
        painting=new PaintingPOJO();
        paintingDAO=new PaintingDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		// TODO Auto-generated method stub
		String path=request.getServletPath();  //getting servlet path to perfomr CRUD operations
		
		//switch case for CRUD
		switch (path) {
		case "/addPainting":  //url path for adding
			savePainting(request, response);  //adding painting
			break;
		case "/viewPaintings": //url path for viewing paintings
			listPaintings(request, response); //listing all paintings
			break;
		case "/updatePaintingForm":  //url path for loading update painting form
			getPaintingById(request, response); //getting all paintings information by lotNumber as id
			break;
		case "/updatePainting":  //url apth for updating painting
			updatePainting(request, response); //updating painting
			break;
		case "/deletePainting"://url path for deleteing painting
			removePainting(request, response); //removing painting 
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	//private method to save painting using Httpservletrequest and response
	private void savePainting(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		//getting value from jsp form using request object
		int lotNumber=Integer.parseInt(request.getParameter("lotNumber"));  
		String artistName=request.getParameter("artistName");
		String yearProduced=request.getParameter("yearProduced");
		String classification=request.getParameter("classification");
		String description=request.getParameter("description");
		String auctionDate=request.getParameter("auctionDate");
		String estimatedPrice=request.getParameter("estimatedPrice");
		String paintingMedium=request.getParameter("paintingMedium");
		String paitningStatus=request.getParameter("status");
		double height=Double.parseDouble(request.getParameter("height"));
		double length=Double.parseDouble(request.getParameter("length"));
		Part part=request.getPart("photo");  //getting image from jsp form
		
		InputStream istream=part.getInputStream();  //input stream for image
		byte [] bytes=IOUtils.toByteArray(istream); //converting image to byte
		
		//settting all the jsp form values to the model classes
		painting.setLotNumber(lotNumber);
		painting.setArtistName(artistName);
		painting.setItemProducedDate(yearProduced);
		painting.setClassification(classification);
		painting.setDescription(description);
		painting.setAuctionDate(auctionDate);
		painting.setEstimated_price(estimatedPrice);
		painting.setPaintingMedium(paintingMedium);
		painting.setStatus(paitningStatus);
		painting.setHeight(height);
		painting.setLength(length);
		painting.setImage(bytes);
		
		paintingDAO.savePainting(painting); //saving painting to database
		response.sendRedirect("viewPaintings");  //redirecting to another page
	}
	
	//method to list all the paintings
	private void listPaintings(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<PaintingPOJO> paintingsList=paintingDAO.getAllPaintings();//getting all the paintings and string to list
		request.setAttribute("paintingsList", paintingsList); //setting painting list to request object
		request.getRequestDispatcher("viewPaintings.jsp").forward(request, response); //loading viewPaintings.jsp page
	}
	
	//method to get painting by id
	private void getPaintingById(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int lotNumber=Integer.parseInt(request.getParameter("id"));  //getting id from url path 
		PaintingPOJO painting=paintingDAO.getPaintingById(lotNumber);//getting painting by id
		request.setAttribute("painting", painting); //setting painting to request object
		request.getRequestDispatcher("updatePaintings.jsp").forward(request, response); //loading updatePaintings.jsp page
	}
	
	//method to update painting 
	private void updatePainting(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		//getting all the values from jsp form
		int lotNumber=Integer.parseInt(request.getParameter("lotNumber"));
		String artistName=request.getParameter("artistName");
		String yearProduced=request.getParameter("yearProduced");
		String classification=request.getParameter("classification");
		String description=request.getParameter("description");
		String auctionDate=request.getParameter("auctionDate");
		String estimatedPrice=request.getParameter("estimatedPrice");
		String paintingMedium=request.getParameter("paintingMedium");
		String paitningStatus=request.getParameter("status");
		double height=Double.parseDouble(request.getParameter("height"));
		double length=Double.parseDouble(request.getParameter("length"));
		Part part=request.getPart("photo");  //getting image from jsp form
		
		InputStream istream=part.getInputStream();  //input stream for image
		byte [] bytes=IOUtils.toByteArray(istream); //converting image to byte using IOUtils to save in database
		
		//getting mtobject id to update according to mtobject_id
		int mt_oid=paintingDAO.getMtoidById(lotNumber);
		
		//setting all the values to model class
		painting.setLotNumber(lotNumber);
		painting.setArtistName(artistName);
		painting.setItemProducedDate(yearProduced);
		painting.setClassification(classification);
		painting.setDescription(description);
		painting.setAuctionDate(auctionDate);
		painting.setEstimated_price(estimatedPrice);
		painting.setPaintingMedium(paintingMedium);
		painting.setStatus(paitningStatus);
		painting.setHeight(height);
		painting.setLength(length);
		painting.setImage(bytes);
		
		paintingDAO.updatePainting(painting, mt_oid); //update painting by mtobject_id
		response.sendRedirect("viewPaintings"); //redirecting to viewPaintings page
	}
	
	//method to remove painting 
	private void removePainting(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int id=Integer.parseInt(request.getParameter("id"));  //getting id from the url path
		paintingDAO.deletePainting(id);  //delete painting by id
		response.sendRedirect("viewPaintings");  //redirecting to view painting page
	}
}
