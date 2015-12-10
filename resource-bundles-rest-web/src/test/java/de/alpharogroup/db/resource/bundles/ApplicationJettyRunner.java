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
import de.alpharogroup.log.LoggerExtensions;
import de.alpharogroup.resourcebundle.properties.PropertiesExtensions;

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
	public static void main(final String[] args) throws Exception
	{
		final int sessionTimeout = 1800;// set timeout to 30min(60sec * 30min=1800sec)...
		final String projectname = "resource-bundles-rest-web";
		final File projectDirectory = PathFinder.getProjectDirectory();
		final File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
			"webapp");

		final String filterPath = "/*";

		final File logfile = new File(projectDirectory, "application.log");
		if(logfile.exists()) {
			try {
				DeleteFileExtensions.delete(logfile);
			} catch (final IOException e) {
				Logger.getRootLogger().error("logfile could not deleted.", e);
			}
		}
		// Add a file appender to the logger programatically
		LoggerExtensions.addFileAppender(Logger.getRootLogger(),
				LoggerExtensions.newFileAppender(logfile.getAbsolutePath()));

		final ServletContextHandler servletContextHandler = ServletContextHandlerFactory.getNewServletContextHandler(
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
				"classpath:application-context.xml")
			.build());
		servletContextHandler.addEventListener(new ContextLoaderListener());
		final Jetty9RunConfiguration configuration = Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler)
			.httpPort(8080)
			.httpsPort(8443)
			.build();
		final Server server = new Server();
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
		final Properties databaseProperties = PropertiesExtensions.loadProperties("jdbc.properties");
		final String hostname = databaseProperties.getProperty("jdbc.host");
		final String databaseName = databaseProperties.getProperty("jdbc.db.name");
		final String databaseUser = databaseProperties.getProperty("jdbc.user");
		final String databasePassword = databaseProperties.getProperty("jdbc.password");
		final boolean dbExists = ConnectionsExtensions.existsPostgreSQLDatabase(hostname, databaseName, databaseUser, databasePassword);
		return dbExists;
	}

}
