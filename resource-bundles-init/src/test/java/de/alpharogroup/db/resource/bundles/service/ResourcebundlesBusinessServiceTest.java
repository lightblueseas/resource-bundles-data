/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
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
package de.alpharogroup.db.resource.bundles.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import de.alpharogroup.db.resource.bundles.entities.Countries;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.PathFinder;

/**
 * The class {@link ResourcebundlesBusinessServiceTest}.
 */
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class ResourcebundlesBusinessServiceTest extends AbstractResourcebundlesBusinessServiceTest
{
	static final boolean DISABLED = false;
	static final boolean ENABLED = true;

	@Override
	@Test(enabled = DISABLED)
	public void testBundleApplicationsWithSameNameResourceBundles()
	{
		super.testBundleApplicationsWithSameNameResourceBundles();
	}

	@Test(enabled = true)
	@Transactional
	public void testCountries() throws IOException
	{
		initCountries();

		final File projectDir = PathFinder.getProjectDirectory();
		final File iso3166A2ToCountryNameFile = PathFinder.getRelativePathTo(projectDir, "/",
			"src/main/resources", "iso3166_a2_countries_names_en.txt");
		List<Countries> list = countriesService.findAll();

		List<String> linesInList = ReadFileExtensions.readLinesInList(iso3166A2ToCountryNameFile);

		for (String line : linesInList)
		{
			String[] keyValue = line.split("=");
			String iso3166A2name = keyValue[0];
			String name = keyValue[1];
			System.out.println(iso3166A2name + "=" + name);
			Countries country = countriesService.find(iso3166A2name);
			if (country != null)
			{
				country.setName(name);
			}
			else
			{
				country = Countries.builder().iso3166a2name(iso3166A2name).name(name).build();
			}
			Countries merged = countriesService.merge(country);

		}


		// List<Countries> availableCountries = DataObjectFactory.newCountries();
		// for (Countries countries : availableCountries)
		// {
		// Countries foundCountry = countriesService.find(countries.getIso3166A2name());
		// if (foundCountry == null)
		// {
		// countriesService.merge(countries);
		// }
		// }
	}

	@Override
	@Test(enabled = true)
	public void testDeleteBundleApplications()
	{
		super.testDeleteBundleApplications();
	}

	@Override
	@Test(enabled = DISABLED)
	public void testDeleteBundleName() throws URISyntaxException, IOException
	{
		super.testDeleteBundleName();
	}

	@Override
	@Test(enabled = DISABLED)
	public void testFindBundleApplications()
	{
		super.testFindBundleApplications();
	}

	@Override
	@Test(enabled = DISABLED)
	public void testFindBundleNames()
	{
		super.testFindBundleNames();
	}

	@Override
	@Test(enabled = DISABLED)
	public void testFindLanguageLocales()
	{
		super.testFindLanguageLocales();
	}

	@Override
	@Test(enabled = DISABLED)
	public void testFindResourceBundles()
	{
		super.testFindResourceBundles();
	}

	@Override
	@Test(enabled = DISABLED)
	public void testUpdateProperties() throws URISyntaxException, IOException
	{
		super.testUpdateProperties();
	}

	@Override
	@Test(enabled = DISABLED)
	public void testUpdatePropertiesUpdate() throws URISyntaxException, IOException
	{
		super.testUpdatePropertiesUpdate();
	}


}
