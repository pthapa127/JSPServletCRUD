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

import com.soft.application.DAOImpl.PhotographicImageDAOImpl;
import com.soft.application.model.PhotographicImagePOJO;

/**
 * Servlet implementation class PhotographicImageController
 */

public class PhotographicImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private PhotographicImagePOJO pimage;
	private PhotographicImageDAOImpl pimageDAO;
    public PhotographicImageController() {
        super();
        // TODO Auto-generated constructor stub
        pimage=new PhotographicImagePOJO();
        pimageDAO=new PhotographicImageDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		// TODO Auto-generated method stub
		String path=request.getServletPath(); //getting servlet path to carry out CRUD operations
		switch (path) {
		case "/addPhotographicImage":
			savePhotographicImage(request, response);  //saving photographic image
			break;
		case "/viewPhotographicImages":
			listPotographicImages(request, response);  //listing all the photographic images
			break;
		case "/updatePhotographicImageForm":
			getPhotographicImageById(request, response);  //getting photographic image by id
			break;
		case "/updatePhotographicImage":
			updatePhotographicImage(request, response);  //updating photographic image
			break;
		case "/deletePhotographicImage":
			removePhotographicImage(request, response); //removing photographic image
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

	private void savePhotographicImage(HttpServletRequest request,HttpServletResponse response)throws 
	ServletException,IOException{
		//setting all the values from the jsp form
		int lotNumber=Integer.parseInt(request.getParameter("lotNumber"));
		String artistName=request.getParameter("artistName");
		String yearProduced=request.getParameter("yearProduced");
		String classification=request.getParameter("classification");
		String description=request.getParameter("description");
		String auctionDate=request.getParameter("auctionDate");
		String estimatedPrice=request.getParameter("estimatedPrice");
		String imageType=request.getParameter("imageType");
		double height=Double.parseDouble(request.getParameter("height"));
		double length=Double.parseDouble(request.getParameter("length"));
		Part part=request.getPart("photo");  //getting iamge from jsp form
		InputStream stream=part.getInputStream();  //input stream IO
		byte[] image=IOUtils.toByteArray(stream); ////converting image to byte
		
		//setting all the property to the model class
		pimage.setLotNumber(lotNumber);
		pimage.setArtistName(artistName);
		pimage.setItemProducedDate(yearProduced);
		pimage.setClassification(classification);
		pimage.setDescription(description);
		pimage.setAuctionDate(auctionDate);
		pimage.setEstimated_price(estimatedPrice);
		pimage.setImageType(imageType);
		pimage.setHeight(height);
		pimage.setLength(length);
		pimage.setImage(image);
		
		pimageDAO.savePhotographicImage(pimage); //saving photographic image
		response.sendRedirect("viewPhotographicImages"); //redirecting
	}
	private void listPotographicImages(HttpServletRequest request,HttpServletResponse response)throws 
	ServletException,IOException{
		List<PhotographicImagePOJO> pis=pimageDAO.getAllPhotographicImages(); //getting all the images and storing in list
		request.setAttribute("photographicImagesList", pis);
		request.getRequestDispatcher("viewPhotographicImages.jsp").forward(request, response);
	}
	
	private void getPhotographicImageById(HttpServletRequest request,HttpServletResponse response)throws 
	ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id")); //getting id from url
		PhotographicImagePOJO pi=pimageDAO.getPhotographicImageById(id);//getting image by id
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("updatePhotographicImages.jsp").forward(request, response);
	}
	
	private void updatePhotographicImage(HttpServletRequest request,HttpServletResponse response)throws 
	ServletException,IOException{
		//getting values from jsp form
		int lotNumber=Integer.parseInt(request.getParameter("id"));
		String artistName=request.getParameter("artistName");
		String yearProduced=request.getParameter("yearProduced");
		String classification=request.getParameter("classification");
		String description=request.getParameter("description");
		String auctionDate=request.getParameter("auctionDate");
		String estimatedPrice=request.getParameter("estimatedPrice");
		String imageType=request.getParameter("imageType");
		double height=Double.parseDouble(request.getParameter("height"));
		double length=Double.parseDouble(request.getParameter("length"));
		Part part=request.getPart("photo");  //getting iamge from jsp form
		InputStream stream=part.getInputStream();  //input stream IO
		byte[] image=IOUtils.toByteArray(stream); ////converting image to byte
		int mtoid=pimageDAO.getmtoidById(lotNumber); //getting mtoid using lotNumber
		
		//setting all the property to the model class
		pimage.setLotNumber(lotNumber);
		pimage.setArtistName(artistName);
		pimage.setItemProducedDate(yearProduced);
		pimage.setClassification(classification);
		pimage.setDescription(description);
		pimage.setAuctionDate(auctionDate);
		pimage.setEstimated_price(estimatedPrice);
		pimage.setImageType(imageType);
		pimage.setHeight(height);
		pimage.setLength(length);
		pimage.setImage(image);
		
		pimageDAO.updatePhotographicImage(pimage, mtoid); //updating
		response.sendRedirect("viewPhotographicImages"); //redirecting
	}
	
	private void removePhotographicImage(HttpServletRequest request,HttpServletResponse response)throws 
	ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id")); //getting id from url//http://localhost:auction?id=123
		pimageDAO.deletePhotographicImage(id); //removing image
		response.sendRedirect("viewPhotographicImages");  //redirecting
	}
}
