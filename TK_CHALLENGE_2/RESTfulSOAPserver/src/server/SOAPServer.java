 
package server;

import javax.xml.ws.Binding;
import javax.xml.ws.Endpoint;
import services.SOAPImpl;

/**
 *
 * @author adam
 */
public class SOAPServer {
    
    private final   SOAPImpl soap; 
    private final  Endpoint ep ; 
    private  Binding bnd;
    private static final String SOAP_URI =
            "http://localhost:8090/services/SoccerTicketsService/SOAP";

    public SOAPServer() {
        soap  = new SOAPImpl();
         ep =Endpoint.create(soap);
         ep.publish(SOAP_URI);
             System.err.println("\n************************************************************");  
         System.err.println("::SOAP WebServices server started : http://localhost:8090::");
            System.err.println("\n************************************************************");  
         
       
        
        
    }
 
    
}
