
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.sf.module.serviceprovide.service;

import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2012-11-12T22:22:45.334+08:00
 * Generated source version: 2.6.2
 * 
 */

@javax.jws.WebService(
                      serviceName = "CustomerServiceService",
                      portName = "CustomerServicePort",
                      targetNamespace = "http://service.serviceprovide.module.sf.com/",
                      wsdlLocation = "http://219.134.187.154:8088/bsp-oip/ws/CustomerService?wsdl",
                      endpointInterface = "com.sf.module.serviceprovide.service.ICustomerService")
                      
public class ICustomerServiceImpl implements ICustomerService {

    private static final Logger LOG = Logger.getLogger(ICustomerServiceImpl.class.getName());

    /* (non-Javadoc)
     * @see com.sf.module.serviceprovide.service.ICustomerService#logisticQueryStandard(java.lang.String  arg0 )*
     */
    public java.lang.String logisticQueryStandard(java.lang.String arg0) { 
        LOG.info("Executing operation logisticQueryStandard");
        System.out.println(arg0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.sf.module.serviceprovide.service.ICustomerService#routeTrackingService(java.lang.String  arg0 )*
     */
    public java.lang.String routeTrackingService(java.lang.String arg0) { 
        LOG.info("Executing operation routeTrackingService");
        System.out.println(arg0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.sf.module.serviceprovide.service.ICustomerService#queryMailnoDetailService(java.lang.String  arg0 )*
     */
    public java.lang.String queryMailnoDetailService(java.lang.String arg0) { 
        LOG.info("Executing operation queryMailnoDetailService");
        System.out.println(arg0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.sf.module.serviceprovide.service.ICustomerService#orderMailBindingService(java.lang.String  arg0 )*
     */
    public java.lang.String orderMailBindingService(java.lang.String arg0) { 
        LOG.info("Executing operation orderMailBindingService");
        System.out.println(arg0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.sf.module.serviceprovide.service.ICustomerService#orderService(java.lang.String  arg0 )*
     */
    public java.lang.String orderService(java.lang.String arg0) { 
        LOG.info("Executing operation orderService");
        System.out.println(arg0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.sf.module.serviceprovide.service.ICustomerService#cancelOrderService(java.lang.String  arg0 )*
     */
    public java.lang.String cancelOrderService(java.lang.String arg0) { 
        LOG.info("Executing operation cancelOrderService");
        System.out.println(arg0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
