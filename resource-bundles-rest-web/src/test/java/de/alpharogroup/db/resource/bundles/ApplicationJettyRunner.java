package de.alpharogroup.db.resource.bundles;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.web.context.ContextLoaderListener;

import de.alpharogroup.file.delete.DeleteFileExtensions;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jdbc.ConnectionsExtensions;
import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.config.Jetty9RunConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletContextHandlerConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletHolderConfiguration;
import de.alpharogroup.jetty9.runner.factories.ServletContextHandlerFactory;
import de.alpharogroup.lang.PropertiesExtensions;
import de.alpharogroup.log.LoggerExtensions;

/**
 * The Class {@link ApplicationJettyRunner} holds the main method that starts a jetty server with the rest services for the resource-bundle-data.
 */
public class ApplicationJettyRunner
{

	/**
	 * The main method starts a jetty server with the rest services for the resource-bundle-data.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception
	{
		int sessionTimeout = 1800;// set timeout to 30min(60sec * 30min=1800sec)...
		String projectname = "resource-bundles-rest-web";
		File projectDirectory = PathFinder.getProjectDirectory();
		File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
			"webapp");
		
		String filterPath = "/*";

		File logfile = new File(projectDirectory, "application.log");
		if(logfile.exists()) {
			try {
				DeleteFileExtensions.delete(logfile);
			} catch (IOException e) {
				Logger.getRootLogger().error("logfile could not deleted.", e);
			}
		}
		// Add a file appender to the logger programatically
		LoggerExtensions.addFileAppender(Logger.getRootLogger(), 
				LoggerExtensions.newFileAppender(logfile.getAbsolutePath()));
		
		ServletContextHandler servletContextHandler = ServletContextHandlerFactory.getNewServletContextHandler(
			ServletContextHandlerConfiguration.builder()
			.servletHolderConfiguration(
				ServletHolderConfiguration.builder()
					.servletClass(CXFServlet.class)
					.pathSpec(filterPath)
					.build())
			.contextPath("/")
			.webapp(webapp)
			.maxInactiveInterval(sessionTimeout)
			.filterPath(filterPath)
			.initParameter("contextConfigLocation",
				"classpath:resource-bundles-application-context.xml")			
			.build());
		servletContextHandler.addEventListener(new ContextLoaderListener());
		Jetty9RunConfiguration configuration = Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler)
			.httpPort(8080)
			.httpsPort(8443)
			.build();
		Server server = new Server();
		Jetty9Runner.runServletContextHandler(server, configuration);

	}

	/**
	 * Checks if a postgresql database exists.
	 *
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 */
	protected static boolean existsPostgreSQLDatabase() throws IOException, ClassNotFoundException, SQLException {
		Properties databaseProperties = PropertiesExtensions.loadProperties("jdbc.properties");
		String hostname = databaseProperties.getProperty("jdbc.host");
		String databaseName = databaseProperties.getProperty("jdbc.db.name");
		String databaseUser = databaseProperties.getProperty("jdbc.user");
		String databasePassword = databaseProperties.getProperty("jdbc.password");
		boolean dbExists = ConnectionsExtensions.existsPostgreSQLDatabase(hostname, databaseName, databaseUser, databasePassword);
		return dbExists;
	}
	
}
