package com.social.ProgettoFinaleSocial.utils;

import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.social.ProgettoFinaleSocial.model.Post;

public class WebUtility {

	public static void aggiungiImmagini(HttpServletRequest request,List<Post> listResult, ServletContext servletContext) throws Exception{

		Map<Integer,String> imagesPath = new HashMap<Integer,String>();
		if ( listResult.size()>0 ) {
			String uploadPath = servletContext.getRealPath("") + File.separator + FinalConstants.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}


			for(Post p : listResult ) {
				String baseHttpUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

				if ( p.getImmagine() != null && p.getImmagine().getBlob() != null) {


					Blob blob = p.getImmagine().getBlob();
					String filePath = uploadPath + File.separator + p.getId() + "_" + p.getImmagine().getNome();
					BlobConverter.saveFile(blob,filePath);
					imagesPath.put(p.getId(),baseHttpUrl + File.separator + FinalConstants.UPLOAD_DIRECTORY + File.separator + p.getId() + "_" + p.getImmagine().getNome());
				}
				else {
					imagesPath.put(p.getId(),baseHttpUrl + File.separator + "risorse" + File.separator + "fac-simile3.png");
				}


			}


		}

		request.setAttribute(FinalConstants.RISULTATI_RICERCA_IMMAGINI,imagesPath);

	}
}


