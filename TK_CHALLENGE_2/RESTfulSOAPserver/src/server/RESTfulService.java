package server;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

/**
 *
 * @author adam
 */
public class RESTfulService {

    public static final String BASE_URI =
            "http://localhost:8080/services/SoccerTicketsService";
    HttpServer server;

    public RESTfulService() throws IOException {
        server = HttpServerFactory.create(BASE_URI);
        try{
        server.start();
        }catch(Exception e){
            
        }
         System.err.println("\n************************************************************");   
        System.err.println("::RESTfull WebServices server started : http://localhost:8080::");
         System.err.println("\n************************************************************");  
    }

     
    
 

}
