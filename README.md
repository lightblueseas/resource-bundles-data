# Overview

This project provides Java resource bundles(i18n) that can be saved to your prefered database and load back. It is kept simple. There is also restful web services provided for providing resource bundles also for the web and can be used also with other languages than java.

# ERD-Diagramm

The erd-diagramm for this database looks as follows: ![erd-diagramm](https://github.com/lightblueseas/resource-bundles-data/blob/develop/resource-bundles-init/src/main/resources/erd/erd-diagramm-resourcesbundles.png)

This erd-diagramm was created with [Jeddict plugin from netbeans as JPA Modeler](http://plugins.netbeans.org/plugin/53057/jpa-modeler)  and [Jeddict](https://jeddict.github.io/)

## License

The source code comes under the liberal MIT License, making resource-bundles-data great for all types of I18N applications.

# Build status
[![Build Status](https://travis-ci.org/lightblueseas/resource-bundles-data.svg?branch=master)](https://travis-ci.org/lightblueseas/resource-bundles-data)

## Maven Central

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/resource-bundles-data/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/resource-bundles-data)

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~resource-bundles-data~~~) for latest snapshots and releases.

Add the following maven dependencies to your project `pom.xml` if you want to import the core functionality:

You can first define the version properties:

	<properties>
			...
		<!-- resource-bundles-data version -->
		<resource-bundles-data.version>5.0.0</resource-bundles-data.version>
		<resource-bundles-business.version>${resource-bundles-data.version}</resource-bundles-business.version>
		<resource-bundles-domain.version>${resource-bundles-data.version}</resource-bundles-domain.version>
		<resource-bundles-entities.version>${resource-bundles-data.version}</resource-bundles-entities.version>
		<resource-bundles-init.version>${resource-bundles-data.version}</resource-bundles-init.version>
		<resource-bundles-rest-api.version>${resource-bundles-data.version}</resource-bundles-rest-api.version>
		<resource-bundles-rest-client.version>${resource-bundles-data.version}</resource-bundles-rest-client.version>
		<resource-bundles-rest-web.version>${resource-bundles-data.version}</resource-bundles-rest-web.version>
			...
	</properties>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of resource-bundles-business:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>resource-bundles-business</artifactId>
				<version>${resource-bundles-business.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of resource-bundles-domain:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>resource-bundles-domain</artifactId>
				<version>${resource-bundles-domain.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of resource-bundles-entities:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>resource-bundles-entities</artifactId>
				<version>${resource-bundles-entities.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of resource-bundles-init:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>resource-bundles-init</artifactId>
				<version>${resource-bundles-init.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of resource-bundles-rest-api:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>resource-bundles-rest-api</artifactId>
				<version>${resource-bundles-rest-api.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of resource-bundles-rest-client:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>resource-bundles-rest-client</artifactId>
				<version>${resource-bundles-rest-client.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of resource-bundles-rest-web:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>resource-bundles-rest-web</artifactId>
				<version>${resource-bundles-rest-web.version}</version>
			</dependency>
			...
		</dependencies>
		 
## Open Issues
[![Open Issues](https://img.shields.io/github/issues/astrapi69/resource-bundles-data.svg?style=flat)](https://github.com/astrapi69/resource-bundles-data/issues) 
		

## Want to Help and improve it? ###

The source code for resource-bundles-data are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [lightblueseas/resource-bundles-data/fork](https://github.com/lightblueseas/resource-bundles-data/fork)

To share your changes, [submit a pull request](https://github.com/lightblueseas/resource-bundles-data/pull/new/master).

Don't forget to add new units tests on your changes.

## Contacting the Developer

Do not hesitate to contact the resource-bundles-data developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/lightblueseas/resource-bundles-data/issues).

## Note

No animals were harmed in the making of this library.

# Donate

If you like this library, please consider a donation through 
<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Fresource-bundles-data" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>

