package com.carpooling.constants;

import java.io.Serializable;

import com.carpooling.businessinterface.ILoginBeanRemote;

public class Constant implements Serializable{
	
	private static final long serialVersionUID = 1L;


	public static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
	
	public static ILoginBeanRemote loginBean; 

	public static String lookUpName(String entityName){
		
		String appName = "WebArchProjectEAR";

		/* The module name is the JAR name of the deployed EJB without the
	        .jar suffix.*/
		String moduleName = "WebArchProject";

		/* AS7 allows each deployment to have an (optional) distinct name.
	        This can be an empty string if distinct name is not specified.*/
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = entityName + "Bean";

		// Fully qualified remote interface name
		final String interfaceName = "com.carpooling.businessinterface.I" + entityName + "BeanRemote" ;
		
		
		String lookup = "ejb:" + appName + "/" + moduleName + "/" +
				distinctName    + "/" + beanName + "!" + interfaceName;

				
		return lookup;
	}
}
