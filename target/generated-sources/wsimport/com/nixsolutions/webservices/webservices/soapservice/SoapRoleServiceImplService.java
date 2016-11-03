
package com.nixsolutions.webservices.webservices.soapservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b14002
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SoapRoleServiceImplService", targetNamespace = "http://soapService.webservices.webServices.nixsolutions.com/", wsdlLocation = "http://localhost:8080/WebServices/soap/roles?wsdl")
public class SoapRoleServiceImplService
    extends Service
{

    private final static URL SOAPROLESERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException SOAPROLESERVICEIMPLSERVICE_EXCEPTION;
    private final static QName SOAPROLESERVICEIMPLSERVICE_QNAME = new QName("http://soapService.webservices.webServices.nixsolutions.com/", "SoapRoleServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/WebServices/soap/roles?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOAPROLESERVICEIMPLSERVICE_WSDL_LOCATION = url;
        SOAPROLESERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public SoapRoleServiceImplService() {
        super(__getWsdlLocation(), SOAPROLESERVICEIMPLSERVICE_QNAME);
    }

    public SoapRoleServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOAPROLESERVICEIMPLSERVICE_QNAME, features);
    }

    public SoapRoleServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SOAPROLESERVICEIMPLSERVICE_QNAME);
    }

    public SoapRoleServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOAPROLESERVICEIMPLSERVICE_QNAME, features);
    }

    public SoapRoleServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SoapRoleServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SoapRoleService
     */
    @WebEndpoint(name = "SoapRoleServiceImplPort")
    public SoapRoleService getSoapRoleServiceImplPort() {
        return super.getPort(new QName("http://soapService.webservices.webServices.nixsolutions.com/", "SoapRoleServiceImplPort"), SoapRoleService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SoapRoleService
     */
    @WebEndpoint(name = "SoapRoleServiceImplPort")
    public SoapRoleService getSoapRoleServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soapService.webservices.webServices.nixsolutions.com/", "SoapRoleServiceImplPort"), SoapRoleService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOAPROLESERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw SOAPROLESERVICEIMPLSERVICE_EXCEPTION;
        }
        return SOAPROLESERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
