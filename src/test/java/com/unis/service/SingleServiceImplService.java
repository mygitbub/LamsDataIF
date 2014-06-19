
package com.unis.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "SingleServiceImplService", targetNamespace = "http://service.unis.com/", wsdlLocation = "http://localhost/LamsDataIF/cxf/ArcDataWsSingle?wsdl")
public class SingleServiceImplService
    extends Service
{

    private final static URL SINGLESERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.unis.service.SingleServiceImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.unis.service.SingleServiceImplService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost/LamsDataIF/cxf/ArcDataWsSingle?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost/LamsDataIF/cxf/ArcDataWsSingle?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SINGLESERVICEIMPLSERVICE_WSDL_LOCATION = url;
    }

    public SingleServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SingleServiceImplService() {
        super(SINGLESERVICEIMPLSERVICE_WSDL_LOCATION, new QName("http://service.unis.com/", "SingleServiceImplService"));
    }

    /**
     * 
     * @return
     *     returns ArcDataWsSingle
     */
    @WebEndpoint(name = "ArcDataWsSinglePort")
    public ArcDataWsSingle getArcDataWsSinglePort() {
        return super.getPort(new QName("http://service.unis.com/", "ArcDataWsSinglePort"), ArcDataWsSingle.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ArcDataWsSingle
     */
    @WebEndpoint(name = "ArcDataWsSinglePort")
    public ArcDataWsSingle getArcDataWsSinglePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.unis.com/", "ArcDataWsSinglePort"), ArcDataWsSingle.class, features);
    }

}