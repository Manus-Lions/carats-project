package com.social.ProgettoFinaleSocial.ui;

import java.io.File;

import java.io.IOException;
import java.sql.Blob;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.social.ProgettoFinaleSocial.businessLogic.BusinessLogic;
import com.social.ProgettoFinaleSocial.exceptions.AlreadyExistentPostException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentDateException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentIdException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentTextException;
import com.social.ProgettoFinaleSocial.exceptions.NotPresentTitleException;
import com.social.ProgettoFinaleSocial.model.Immagine;
import com.social.ProgettoFinaleSocial.model.Post;
import com.social.ProgettoFinaleSocial.model.Utente;
import com.social.ProgettoFinaleSocial.utils.BlobConverter;
import com.social.ProgettoFinaleSocial.utils.FinalConstants;

/**
 * Servlet implementation class AddPostServlet
 */
@WebServlet("/AddPostServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10 * 5)

public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	//SCRITTO E DA TESTARE
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			BusinessLogic BS = (BusinessLogic) getServletContext().getAttribute(FinalConstants.BUSINESSLOGIC);

			String titolo = request.getParameter(FinalConstants.INPUT_TITOLOPOST);
			String testo = request.getParameter(FinalConstants.INPUT_TESTOPOST);
		
			Utente autore =  (Utente) request.getSession().getAttribute(FinalConstants.INPUT_UTENTE);

		//	System.out.println(titolo + testo + autore.getNome());
			  	
			String uploadPath = getServletContext().getRealPath("") + File.separator + FinalConstants.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			Immagine immagine = null;
			String filePath = null;
			//request.getParts() restituisce le parti della form
			for ( Part part : request.getParts() ) {
				//getSubmittedFileName non è null solo dove ci sarà un file
				String fileName = part.getSubmittedFileName();
				if ( fileName!=null && !fileName.isEmpty() ) {
					filePath = uploadPath + File.separator + fileName;
					part.write(filePath);
					Blob blob = BlobConverter.generateBlob(filePath);
					
					// file  Path = getServletContext().getRealPath("") + File.separator + FinalConstants.UPLOAD_DIRECTORY + File.separatore + 
					immagine = new Immagine();
					
					immagine.setNome(fileName);
					immagine.setBlob(blob);
					
						
					
					
			
					
				
					
				
					
//					prodotto.setImmagine(blob);
//					prodotto.setNomeFileImmagine(fileName);
				}
			}

			Post post = new Post(titolo, testo, LocalDateTime.now() ,immagine, autore);

			BS.insertPost(post, autore.getId());
		
			response.sendRedirect("MyPostServlet");
		}
		catch ( AlreadyExistentPostException  | NotPresentDateException | 
				NotPresentIdException | NotPresentTitleException | NotPresentTextException e ) {

			e.printStackTrace();

			request.setAttribute(FinalConstants.MESSAGGIO, e.getMessage());
			request.getRequestDispatcher("HomeServlet").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute(FinalConstants.MESSAGGIO, "Errore imprevisto");
			request.getRequestDispatcher("/jsp/Esito.jsp").forward(request, response);
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
