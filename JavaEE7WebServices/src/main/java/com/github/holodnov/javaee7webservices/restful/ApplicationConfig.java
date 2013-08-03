package com.github.holodnov.javaee7webservices.restful;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 
 * @author Kyrylo Holodnov
 */
@ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
	Set<Class<?>> resources = new HashSet<Class<?>>();
	// following code can be used to customize Jersey 2.0 JSON provider:
	try {
	    Class jsonProvider = Class
		    .forName("org.glassfish.jersey.jackson.JacksonFeature");
	    // Class jsonProvider = Class.forName("org.glassfish.jersey.moxy.json.MoxyJsonFeature");
	    // Class jsonProvider = Class.forName("org.glassfish.jersey.jettison.JettisonFeature");
	    resources.add(jsonProvider);
	} catch (ClassNotFoundException ex) {
	    ex.printStackTrace();
	}
	addRestResourceClasses(resources);
	return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
	resources
		.add(com.github.holodnov.javaee7webservices.restful.EsRoleFacadeREST.class);
	resources
		.add(com.github.holodnov.javaee7webservices.restful.EsUserFacadeREST.class);
    }
}
