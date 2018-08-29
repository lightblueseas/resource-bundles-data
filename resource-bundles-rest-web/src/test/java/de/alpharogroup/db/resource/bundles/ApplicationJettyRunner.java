/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
import de.alpharogroup.resourcebundle.properties.PropertiesFileExtensions;

/**
 * The Class {@link ApplicationJettyRunner} holds the main method that starts a jetty server with
 * the rest services for the resource-bundle-data.
 */
public class ApplicationJettyRunner
{

	/**
	 * Checks if a postgresql database exists.
	 *
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	protected static boolean existsPostgreSQLDatabase()
		throws IOException, ClassNotFoundException, SQLException
	{
		final Properties databaseProperties = PropertiesFileExtensions
			.loadProperties("jdbc.properties");
		final String hostname = databaseProperties.getProperty("jdbc.host");
		final String databaseName = databaseProperties.getProperty("jdbc.db.name");
		final String databaseUser = databaseProperties.getProperty("jdbc.user");
		final String databasePassword = databaseProperties.getProperty("jdbc.password");
		final boolean dbExists = ConnectionsExtensions.existsPostgreSQLDatabase(hostname,
			databaseName, databaseUser, databasePassword);
		return dbExists;
	}

	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected static String getProjectName() throws IOException
	{
		final Properties projectProperties = PropertiesFileExtensions
			.loadProperties("project.properties");
		final String projectName = projectProperties.getProperty("artifactId");
		return projectName;
	}

	/**
	 * The main method starts a jetty server with the rest services for the resource-bundle-data.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(final String[] args) throws Exception
	{
		final int sessionTimeout = 1800;// set timeout to 30min(60sec *
										// 30min=1800sec)...
		final String projectname = getProjectName();
		final File projectDirectory = PathFinder.getProjectDirectory();
		final File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
			"webapp");

		final String filterPath = "/*";

		final File logfile = new File(projectDirectory, "application.log");
		if (logfile.exists())
		{
			try
			{
				DeleteFileExtensions.delete(logfile);
			}
			catch (final IOException e)
			{
				Logger.getRootLogger().error("logfile could not deleted.", e);
			}
		}
		// Add a file appender to the logger programatically
		LoggerExtensions.addFileAppender(Logger.getRootLogger(),
			LoggerExtensions.newFileAppender(logfile.getAbsolutePath()));

		final ServletContextHandler servletContextHandler = ServletContextHandlerFactory
			.getNewServletContextHandler(ServletContextHandlerConfiguration.builder()
				.servletHolderConfiguration(ServletHolderConfiguration.builder()
					.servletClass(CXFServlet.class).pathSpec(filterPath).build())
				.contextPath("/").webapp(webapp).maxInactiveInterval(sessionTimeout)
				.filterPath(filterPath)
				.initParameter("contextConfigLocation", "classpath:application-context.xml")
				.build());
		servletContextHandler.addEventListener(new ContextLoaderListener());
		final Jetty9RunConfiguration configuration = Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler).httpPort(8080).httpsPort(8443)
			.keyStorePassword("wicket").keyStorePathResource("/keystore.ks").build();
		final Server server = new Server();
		Jetty9Runner.runServletContextHandler(server, configuration);

	}

}
