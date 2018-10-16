package com.leosal.medrepear.util;

import java.util.Properties;
import java.util.prefs.Preferences;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JUnitUtil {
	private static Preferences prefs = 
			Preferences.userNodeForPackage(com.leosal.medrepear.service.ConnectionManager.class);
	public static Properties getInitProperties(){
		Properties result = new Properties();
		// We need to tell the context where and how to look
        result.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");
        result.setProperty("java.naming.factory.url.pkgs",
                "com.sun.enterprise.naming");
        result.setProperty("java.naming.factory.state",
                "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
       

        // Should not be necessary for local test (default values), but
        // currently is
        //System.setProperty("MEDREP_HOST", "localhost");
        String host = prefs.get("MEDREP_HOST", ""); // localhost, 37.233.53.95 My IP
        if(host==""){
        	host = "localhost";
        	prefs.put("MEDREP_HOST", host);
        }
        String port = prefs.get("MEDREP_PORT", "3700");
        System.out.println("Connecting to "+host+":"+port);
        result.setProperty("org.omg.CORBA.ORBInitialHost", host.trim());
        result.setProperty("org.omg.CORBA.ORBInitialPort", port.trim());

        return result;
	}
	
	public static InitialContext getInitialContext() throws NamingException{
		//InitialContext context = new InitialContext(getInitProperties());
		String host = prefs.get("MEDREP_HOST", ""); // localhost, 37.233.53.95 My IP
        if(host==""){
        	host = "localhost";
        	prefs.put("MEDREP_HOST", host);
        }
        String port = prefs.get("MEDREP_PORT", "3700");
        System.out.println("Connecting to "+host+":"+port);
        System.setProperty("org.omg.CORBA.ORBInitialHost", host.trim());
        System.setProperty("org.omg.CORBA.ORBInitialPort", port.trim());
        
		return new InitialContext();
	}
}
