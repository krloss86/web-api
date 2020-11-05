package ar.com.educacionit.web.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secure/imageDownload")
public class ImageDownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	String id = req.getParameter("id");
    	
    	ObjetoImagenDto objImagen = buildObjetoDto();
    	
    	ServletOutputStream outputStream;
    	
    	if (objImagen != null) {
    		
	        if (objImagen.getDatos() == null) {
	        	objImagen.setDatos(new byte[0]);
	        }
	
	        resp.setContentType(objImagen.getTipoContenido());
	        resp.setHeader("Pragma", "no-cache");
	        resp.setDateHeader("Expires", 0);
	        resp.setContentLength(objImagen.getDatos().length);
	        resp.setHeader("Content-Disposition", "inline; filename=" + objImagen.getNombreArchivo());
	        
			outputStream = resp.getOutputStream();
	        outputStream.write(objImagen.getDatos());
	        outputStream.flush();
    	
    	} else {
    		outputStream = resp.getOutputStream();
    		outputStream.flush();
    	}
    }
    
    private ObjetoImagenDto buildObjetoDto() throws IOException {
		String urlImagen = "https://www.com.ar/sicame/api/files/download?url=/sicame/2020/cautelares/acta_1_500e17c1-71d0-4447-ba49-677a1b3f4d76.jpg";
		ObjetoImagenDto imagen = new ObjetoImagenDto();
		imagen.setNombreArchivo("Pago");
		imagen.setDatos(obtenerArchivoExterno(urlImagen));
		imagen.setFechaHoraCreacion(new Date());
		imagen.setTipoContenido("image/jpeg");
		return imagen;
	}

	/**
	 * Realiza la consulta al servicio de imagenes
	 * @param urlImagen
	 * @return
	 */
	private byte[] obtenerArchivoExterno(String urlImagen) throws IOException {

		String urlResoruce = urlImagen;
		
	    return getByteFromUrl(urlResoruce);
	  }

	private byte[] getByteFromUrl(String urlResoruce) throws IOException {
		// many of these calls can throw exceptions, so i've just
	    // wrapped them all in one try/catch statement.
		byte[] data = new byte[16384];
		
		try {
	      // create a url object
	      URL url = new URL(urlResoruce);

	      // create a urlconnection object
	      URLConnection urlConnection = url.openConnection();

	      InputStream is = urlConnection.getInputStream();
	     
	      ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	      int nRead;

	      while ((nRead = is.read(data, 0, data.length)) != -1) {
	        buffer.write(data, 0, nRead);
	      }

	      is.close();

	      return buffer.toByteArray();
	      
	    }
	    catch(Exception e) {
	    	throw new IOException("Error al recuperar :" + urlResoruce, e);
	    }
	}
}