package com.soft.application.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soft.application.DAOImpl.CarvingDAOImpl;
import com.soft.application.DAOImpl.DrawingDAOImpl;
import com.soft.application.DAOImpl.PaintingDAOImpl;
import com.soft.application.DAOImpl.PhotographicImageDAOImpl;
import com.soft.application.DAOImpl.SculptureDAOImpl;
import com.soft.application.model.CarvingPOJO;
import com.soft.application.model.DrawingPOJO;
import com.soft.application.model.PaintingPOJO;
import com.soft.application.model.PhotographicImagePOJO;
import com.soft.application.model.SculpturePOJO;

/**
 * Servlet implementation class ClientController
 */

//this class is responsible for viewing all the items to client user
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private PaintingDAOImpl paintingDAO;
	private SculptureDAOImpl sculptureDAO;
	private PhotographicImageDAOImpl piDAO;
	private CarvingDAOImpl carvingDAO;
	private DrawingDAOImpl drawingDAO;
    public ClientController() {
        super();
        // TODO Auto-generated constructor stub
        paintingDAO=new PaintingDAOImpl();
        sculptureDAO=new SculptureDAOImpl();
        piDAO=new PhotographicImageDAOImpl();
        carvingDAO=new CarvingDAOImpl();
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
		case "/viewPaintingsForUser":
			viewPaintings(request, response);
			break;
			
		case "/viewPhotographicImagesForUser":
			viewPhotographicImages(request, response);
			break;
			
		case"/viewSculpturesForUser":
			viewSculptures(request, response);
			break;
			
		case "/viewCarvingsForUser":
			viewCarvings(request, response);
			break;
		case "/viewDrawingsForUser":
			viewDrawings(request, response);
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
	
	//view paintings for client user
	private void viewPaintings(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<PaintingPOJO> paintings=paintingDAO.getAllPaintings();
		request.setAttribute("paintingsList", paintings);
		request.getRequestDispatcher("viewPaintingsForUser.jsp").forward(request, response);
	}
	
	//view photographic image for client user
	private void viewPhotographicImages(HttpServletRequest request,HttpServletResponse response)throws 
	ServletException,IOException{
		List<PhotographicImagePOJO> pis=piDAO.getAllPhotographicImages();
		request.setAttribute("photographicImagesList", pis);
		request.getRequestDispatcher("viewPhotographicImagesForUser.jsp").forward(request, response);
	}
	
	//view sculpture for client user
	private void viewSculptures(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<SculpturePOJO> sculptures=sculptureDAO.getAllSculptures();
		request.setAttribute("sculpturesList", sculptures);
		request.getRequestDispatcher("viewSculpturesForUser.jsp").forward(request, response);
	}
	
	//view carving for client user
	private void viewCarvings(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<CarvingPOJO> carvings=carvingDAO.getAllCarvings();
		request.setAttribute("carvingsList", carvings);
		request.getRequestDispatcher("viewCarvingsForUser.jsp").forward(request, response);
	}
	
	//view drawing for client user
	private void viewDrawings(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<DrawingPOJO> drawings=drawingDAO.getAllDrawings();
		request.setAttribute("drawingsList", drawings);
		request.getRequestDispatcher("viewDrawingsForUser.jsp").forward(request, response);
	}

}
