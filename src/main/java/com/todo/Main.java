package com.todo;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.todo.log.Logger;
import com.todo.log.LoggerConfigurator;
import com.todo.log.LoggerFactory;
import com.todo.properties.WebServiceProperties;

/**
 * Main class.
 *
 */
public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		LoggerConfigurator.configure();

		final HttpServer server = startServer(WebServiceProperties.SERVICE_URI);

		LOGGER.log(String
				.format("Web service started running with WADL available at %sapplication.wadl\nHit enter to stop it...",
						WebServiceProperties.SERVICE_URI));

		stopServerOnKeyPress(server);
	}

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer(String serviceUrl) {
		// create a resource config that scans for JAX-RS resources and
		// providers in com.todo package
		final ResourceConfig rc = new ResourceConfig()
				.packages(WebServiceProperties.JERSEY_RESOURCE_PACKAGE);

		// create and start a new instance of grizzly http server exposing the
		// Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(
				URI.create(serviceUrl), rc);
	}

	/**
	 * Stops the server on key press by user
	 * 
	 * @param server
	 * @throws IOException
	 */
	private static void stopServerOnKeyPress(HttpServer server)
			throws IOException {
		System.in.read();
		server.shutdownNow();
	}
}
