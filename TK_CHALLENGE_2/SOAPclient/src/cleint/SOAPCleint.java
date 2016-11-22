 
 package cleint;

import java.net.MalformedURLException;
import java.net.URL;
 
 
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
 
/**
 *
 * @author adam
 */
public class SOAPCleint {

  
        private final  URL url;
        private final QName qname ;
        private final    Service service;
        public     services.SOAPService remote;

     public SOAPCleint() throws MalformedURLException {
        
      url = new URL("http://localhost:8090/services/SoccerTicketsService/SOAP?wsdl");
      qname = new QName("http://SoccerTicketsService/", "SOAP");
      service = Service.create(url, qname);
      remote = service.getPort( services.SOAPService.class);
  
       
    }
    
        
 
}
