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

import com.soft.application.DAOImpl.DrawingDAOImpl;
import com.soft.application.model.DrawingPOJO;

/**
 * Servlet implementation class DrawingController
 */

public class DrawingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DrawingPOJO drawing;
	private DrawingDAOImpl drawingDAO;
    public DrawingController() {
        super();
        // TODO Auto-generated constructor stub
        drawing=new DrawingPOJO();
        drawingDAO=new DrawingDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		// TODO Auto-generated method stub
		String path=request.getServletPath();
		
		switch (path) {
		case "/addDrawing":
			saveDrawing(request, response);
			break;
		case "/viewDrawings":
			listDrawings(request, response);
			break;
		case "/updateDrawingForm":
			getDrawingById(request, response);
			break;
		case "/updateDrawing":
			updateDrawings(request, response);
			break;
		case "/deleteDrawing":
			removeDrawings(request, response);
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

	private void saveDrawing(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//getting all the values from the jsp form
		int lotNumber=Integer.parseInt(request.getParameter("lotNumber"));
		String artistName=request.getParameter("artistName");
		String yearProduced=request.getParameter("yearProduced");
		String classification=request.getParameter("classification");
		String description=request.getParameter("description");
		String auctionDate=request.getParameter("auctionDate");
		String estimatedPrice=request.getParameter("estimatedPrice");
		String drawingMedium=request.getParameter("drawingMedium");
		String drawingStatus=request.getParameter("status");
		double length=Double.parseDouble(request.getParameter("length"));
		double height=Double.parseDouble(request.getParameter("height"));
		Part part=request.getPart("photo");
		InputStream stream=part.getInputStream();
		byte []image=IOUtils.toByteArray(stream);
		
		//setting values to the model class
		drawing.setLotNumber(lotNumber);
		drawing.setArtistName(artistName);
		drawing.setItemProducedDate(yearProduced);
		drawing.setClassification(classification);
		drawing.setDescription(description);
		drawing.setAuctionDate(auctionDate);
		drawing.setEstimated_price(estimatedPrice);
		drawing.setDrawingMedium(drawingMedium);
		drawing.setStatus(drawingStatus);
		drawing.setLength(length);
		drawing.setHeight(height);
		drawing.setImage(image);
		
		drawingDAO.saveDrawing(drawing);  //saving drawing
		response.sendRedirect("viewDrawings");  //redirecting to view drawings jsp page
	}
	private void listDrawings(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<DrawingPOJO> drawings=drawingDAO.getAllDrawings();
		request.setAttribute("drawingsList", drawings);
		request.getRequestDispatcher("viewDrawings.jsp").forward(request, response);
	}
	
	private void getDrawingById(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		DrawingPOJO drawing=drawingDAO.getDrawingById(id);
		request.setAttribute("drawing", drawing);
		request.getRequestDispatcher("updateDrawings.jsp").forward(request, response);
	}
	
	private void updateDrawings(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		//getting all the values from the jsp form
				int lotNumber=Integer.parseInt(request.getParameter("id"));
				String artistName=request.getParameter("artistName");
				String yearProduced=request.getParameter("yearProduced");
				String classification=request.getParameter("classification");
				String description=request.getParameter("description");
				String auctionDate=request.getParameter("auctionDate");
				String estimatedPrice=request.getParameter("estimatedPrice");
				String drawingMedium=request.getParameter("drawingMedium");
				String drawingStatus=request.getParameter("status");
				double length=Double.parseDouble(request.getParameter("length"));
				double height=Double.parseDouble(request.getParameter("height"));
				Part part=request.getPart("photo");  //getting image from jsp form
				InputStream stream=part.getInputStream();  //inputstream io
				byte[] image=IOUtils.toByteArray(stream);  //converting to byte array using IOUtils
				int mtoid=drawingDAO.getMtoidById(lotNumber); //getting oid by lotNumber
				
				//setting values to the model class
				drawing.setLotNumber(lotNumber);
				drawing.setArtistName(artistName);
				drawing.setItemProducedDate(yearProduced);
				drawing.setClassification(classification);
				drawing.setDescription(description);
				drawing.setAuctionDate(auctionDate);
				drawing.setEstimated_price(estimatedPrice);
				drawing.setDrawingMedium(drawingMedium);
				drawing.setStatus(drawingStatus);
				drawing.setLength(length);
				drawing.setHeight(height);
				drawing.setImage(image);//setting image as a byte to update
				
				drawingDAO.updateDrawing(drawing, mtoid);//updating drawing
				response.sendRedirect("viewDrawings");  //redirecting to view drawings jsp page
	}
	
	private void removeDrawings(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		
		int id=Integer.parseInt(request.getParameter("id"));
		drawingDAO.deleteDrawing(id);
		response.sendRedirect("viewDrawings");
	}
}
