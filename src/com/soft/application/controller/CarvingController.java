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

import com.soft.application.DAOImpl.CarvingDAOImpl;
import com.soft.application.model.CarvingPOJO;

/**
 * Servlet implementation class CarvingController
 */

public class CarvingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CarvingPOJO carving;  //carving model class
	private CarvingDAOImpl carvingDAO;  //DAO class that contain all the CRUD logics
    public CarvingController() {
        super();
        // TODO Auto-generated constructor stub
        carving=new CarvingPOJO();  
        carvingDAO=new CarvingDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		// TODO Auto-generated method stub
		String path=request.getServletPath();  //getting path of servlet 
		switch (path) {
		case "/addCarving":
			saveCarving(request, response); //save
			break;
		case "/viewCarvings":
			listAllCarvings(request, response); //list all carvings
			break;
		case "/updateCarvingForm":
			getCarvingById(request, response); //get carving by id
			break;
		case "/updateCarving":
			updateCarving(request, response);  //update carving
			break;
		case "/deleteCarving":
			removeCarving(request, response);  //delete carving
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

	//private method for saving carvings
	private void saveCarving(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		//getting all the value from jsp forms
		int lotNumber=Integer.parseInt(request.getParameter("lotNumber"));
		String artistName=request.getParameter("artistName");
		String yearProduced=request.getParameter("yearProduced");
		String classification=request.getParameter("classification");
		String description=request.getParameter("description");
		String auctionDate=request.getParameter("auctionDate");
		String estimatedPrice=request.getParameter("estimatedPrice");
		String materialUsed=request.getParameter("materialUsed");
		double length=Double.parseDouble(request.getParameter("length"));
		double height=Double.parseDouble(request.getParameter("height"));
		double width=Double.parseDouble(request.getParameter("width"));
		double weight=Double.parseDouble(request.getParameter("weight"));
		Part part=request.getPart("photo");  //getting image from jsp form
		
		InputStream stream=part.getInputStream(); //input stream from IO
		byte [] bytes=IOUtils.toByteArray(stream); //converting image to byte
		
		//setting all the values from the jsp form to model class
		carving.setLotNumber(lotNumber);
		carving.setArtistName(artistName);
		carving.setItemProducedDate(yearProduced);
		carving.setClassification(classification);
		carving.setDescription(description);
		carving.setAuctionDate(auctionDate);
		carving.setEstimated_price(estimatedPrice);
		carving.setMaterialUsed(materialUsed);
		carving.setLength(length);
		carving.setHeight(height);
		carving.setWidth(width);
		carving.setWeight(weight);
		carving.setImage(bytes);
		
		carvingDAO.saveCarving(carving);  //saving carving to database
		response.sendRedirect("viewCarvings");  //redirecting to view carvings page
	}
	
	//private method to list all the carvings
	private void listAllCarvings(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<CarvingPOJO> carvings=carvingDAO.getAllCarvings();  //getting all the carvings and storing to the list
		request.setAttribute("carvingsList", carvings);  //setting carving list to request object
		request.getRequestDispatcher("viewCarvings.jsp").forward(request, response);  //loading carving.jsp page
	}
	
	private void getCarvingById(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		CarvingPOJO carving=carvingDAO.getCarvingById(id);
		request.setAttribute("carving", carving);
		request.getRequestDispatcher("updateCarvings.jsp").forward(request, response);
	}
	
	private void updateCarving(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		//getting all the value from jsp forms
				int lotNumber=Integer.parseInt(request.getParameter("lotNumber"));
				String artistName=request.getParameter("artistName");
				String yearProduced=request.getParameter("yearProduced");
				String classification=request.getParameter("classification");
				String description=request.getParameter("description");
				String auctionDate=request.getParameter("auctionDate");
				String estimatedPrice=request.getParameter("estimatedPrice");
				String materialUsed=request.getParameter("materialUsed");
				double length=Double.parseDouble(request.getParameter("length"));
				double height=Double.parseDouble(request.getParameter("height"));
				double width=Double.parseDouble(request.getParameter("width"));
				double weight=Double.parseDouble(request.getParameter("weight"));
				Part part=request.getPart("photo");  //getting image from jsp form
				
				InputStream stream=part.getInputStream(); //input stream from IO
				byte [] bytes=IOUtils.toByteArray(stream); //converting image to byte
				
				int mtoid=carvingDAO.getMtOidById(lotNumber);  //getting oid from lotnumber
				
				//setting all the values from the jsp form to model class
				carving.setLotNumber(lotNumber);
				carving.setArtistName(artistName);
				carving.setItemProducedDate(yearProduced);
				carving.setClassification(classification);
				carving.setDescription(description);
				carving.setAuctionDate(auctionDate);
				carving.setEstimated_price(estimatedPrice);
				carving.setMaterialUsed(materialUsed);
				carving.setLength(length);
				carving.setHeight(height);
				carving.setWidth(width);
				carving.setWeight(weight);
				carving.setImage(bytes);
				
				carvingDAO.updateCarving(carving, mtoid); //updating carving according to mtoid
				response.sendRedirect("viewCarvings");  //redirecting to view carvings page

	}
	
	private void removeCarving(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int id=Integer.parseInt(request.getParameter("id"));  //getting id from the url to remove carving
		carvingDAO.deleteCarving(id);  //removing carving according to id
		response.sendRedirect("viewCarvings");  //redirecting to view carvings page
	}
}
