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

import com.soft.application.DAOImpl.SculptureDAOImpl;
import com.soft.application.model.SculpturePOJO;

/**
 * Servlet implementation class SculptureController
 */

public class SculptureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private SculpturePOJO sculpture;
	private SculptureDAOImpl sculptureDAO;
    public SculptureController() {
        super();
        // TODO Auto-generated constructor stub
        sculpture=new SculpturePOJO();
        sculptureDAO=new SculptureDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		// TODO Auto-generated method stub
		String path=request.getServletPath();
		
		switch (path) {
		case "/addSculpture":
			saveSculpture(request, response);
			break;
		case "/viewSculptures":
			listSculptures(request, response);
			break;
		case "/updateSculptureForm":
			getSculptureById(request, response);
			break;
		case "/updateSculpture":
			updateSculpture(request, response);
			break;
		case "/deleteSculpture":
			removeSculpture(request, response);
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

	private void saveSculpture(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
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
		Part part=request.getPart("photo");
		InputStream stream=part.getInputStream();
		byte[] image=IOUtils.toByteArray(stream);
		
		sculpture.setLotNumber(lotNumber);
		sculpture.setArtistName(artistName);
		sculpture.setItemProducedDate(yearProduced);
		sculpture.setClassification(classification);
		sculpture.setDescription(description);
		sculpture.setAuctionDate(auctionDate);
		sculpture.setEstimated_price(estimatedPrice);
		sculpture.setMaterialUsed(materialUsed);
		sculpture.setLength(length);
		sculpture.setHeight(height);
		sculpture.setWidth(width);
		sculpture.setWeight(weight);
		sculpture.setImage(image);
		
		sculptureDAO.saveSculpture(sculpture);
		response.sendRedirect("viewSculptures");
	}
	private void listSculptures(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<SculpturePOJO> sculptures=sculptureDAO.getAllSculptures();
		request.setAttribute("sculpturesList", sculptures);
		request.getRequestDispatcher("viewSculptures.jsp").forward(request, response);
	}
	
	private void getSculptureById(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		SculpturePOJO sculpture=sculptureDAO.getSculptureById(id);
		request.setAttribute("sculpture", sculpture);
		request.getRequestDispatcher("updateSculptures.jsp").forward(request, response);
	}
	
	private void removeSculpture(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		sculptureDAO.deleteSculpturePOJO(id);
		response.sendRedirect("viewSculptures");
	}
	
	private void updateSculpture(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int lotNumber=Integer.parseInt(request.getParameter("id"));
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
		Part part=request.getPart("photo");
		InputStream stream=part.getInputStream();
		byte[] image=IOUtils.toByteArray(stream);
		
		int mtoid=sculptureDAO.getmtoidbyId(lotNumber);
		sculpture.setLotNumber(lotNumber);
		sculpture.setArtistName(artistName);
		sculpture.setItemProducedDate(yearProduced);
		sculpture.setClassification(classification);
		sculpture.setDescription(description);
		sculpture.setAuctionDate(auctionDate);
		sculpture.setEstimated_price(estimatedPrice);
		sculpture.setMaterialUsed(materialUsed);
		sculpture.setLength(length);
		sculpture.setHeight(height);
		sculpture.setWidth(width);
		sculpture.setWeight(weight);
		sculpture.setImage(image);
		
		sculptureDAO.updateSculpture(sculpture, mtoid);
		response.sendRedirect("viewSculptures");
	}
}
