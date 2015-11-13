package de.alpharogroup.db.resource.bundles;

import java.io.File;
import java.io.IOException;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.web.context.ContextLoaderListener;

import de.alpharogroup.file.delete.DeleteFileUtils;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.config.Jetty9RunConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletContextHandlerConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletHolderConfiguration;
import de.alpharogroup.jetty9.runner.factories.ServletContextHandlerFactory;

/**
 * The Class ApplicationJettyRunner holds the main method that starts a jetty server with the rest services for the resource-bundle-data.
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
				DeleteFileUtils.delete(logfile);
			} catch (IOException e) {
				Logger.getRootLogger().error("logfile could not deleted.", e);
			}
		}
//		String absolutePathFromLogfile = logfile.getAbsolutePath();
		// Add a file appender to the logger programatically
//		Logger.getRootLogger().addFileAppender(LoggerExtensions.newFileAppender(absolutePathFromLogfile));

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
}
