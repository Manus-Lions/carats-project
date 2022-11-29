package com.social.ProgettoFinaleSocial.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;

import org.hibernate.engine.jdbc.BlobProxy;

public class BlobConverter {

	public static Blob generateBlob(String filePath) throws Exception{
		
		InputStream is = new FileInputStream(filePath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		//carico il file in lettura in un array di byte, byte per byte
		byte [] buffer = new byte[1024];
		int read = -1;
		
		//primo parametro:dove salvo i byte letti; secondo parametro: indice del primo byte da leggere; terzo parametro: termine di lettura.
		//il metodo is.read restituisce il numero di byte letti, e quando non trova pià niente da leggere restitisce -1 (false).
		//il metodo isread legge e mantiene traccia della posizione da cui riprendere la lettura
		while ( (read = is.read(buffer, 0, buffer.length)) != -1)  {
			
			baos.write(buffer, 0, read);
		}
		baos.flush();
		
		byte [] data = baos.toByteArray();
		Blob result = BlobProxy.generateProxy(data);
		baos.close();
		is.close();
		return result;
	}
	
	//prendo dal database un blob e lo salvo in una cartella del server

	public static void saveFile(Blob blob,String filePath) throws Exception {
		
		InputStream is = blob.getBinaryStream();
    	FileOutputStream fos = new FileOutputStream(filePath);
		byte [] buffer = new byte[1024];
		int read = -1;
		while ( (read = is.read(buffer, 0, buffer.length)) != -1)  {
			fos.write(buffer, 0, read);
		}
		fos.flush();
		fos.close();
	}

	
}
