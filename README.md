# Overview


<div align="center">

[![Build Status](https://travis-ci.org/lightblueseas/resource-bundles-data.svg?branch=master)](https://travis-ci.org/lightblueseas/resource-bundles-data)
[![Open Issues](https://img.shields.io/github/issues/lightblueseas/resource-bundles-data.svg?style=flat)](https://github.com/lightblueseas/resource-bundles-data/issues) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/resource-bundles-data/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/resource-bundles-data)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

</div>

This project provides Java resource bundles(i18n) that can be saved to your prefered database and load back. It is kept simple. There is also restful web services provided for providing resource bundles also for the web and can be used also with other languages than java.

# ERD-Diagramm

The erd-diagramm for this database looks as follows: ![erd-diagramm](https://github.com/lightblueseas/resource-bundles-data/blob/develop/resource-bundles-init/src/main/resources/erd/erd-diagramm-resourcesbundles.png)

This erd-diagramm was created with [intellij](https://www.jetbrains.com/)

## License

The source code comes under the liberal MIT License, making resource-bundles-data great for all types of I18N applications.

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~resource-bundles-data~~~) for latest snapshots and releases.

Add the following maven dependencies to your project `pom.xml` if you want to import the core functionality:

You can first define the version properties:

	<properties>
			...
		<!-- resource-bundles-data version -->
		<resource-bundles-data.version>5.2.0</resource-bundles-data.version>
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

# Donations

This project is kept as an open source product and relies on contributions to remain being developed. 
If you like this project, please consider a donation through paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=B37J9DZF6G9ZC" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

1Jzso5h7U82QCNmgxxSCya1yUK7UVcSXsW

or over ether with:

0xaB6EaE10F352268B0CA672Dd6e999C86344D49D8

or over flattr: <a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Fresource-bundles-data" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>

## Credits

|Travis CI|
|:-:|
|![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)|
|Many thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects.|

