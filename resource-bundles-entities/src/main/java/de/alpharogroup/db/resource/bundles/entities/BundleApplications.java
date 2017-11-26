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
package de.alpharogroup.db.resource.bundles.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import de.alpharogroup.db.entity.name.unique.ExtraLargeUNameBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The entity class {@link BundleApplications} is the root of every bundle application. Every entity
 * of type {@link BundleNames} has as reference to a {@link BundleApplications}. Every entity class
 * of {@link BundleApplications} has exactly one default locale that is mandatory. If you see it
 * from the properties file view it is the default properties file is the properties file without
 * the locale suffix. The entity class {@link BundleApplications} acts as the manager of a
 * corresponding real application like a web application or an android-app. If the real application
 * supports only one locale the default locale is enough but if the real application supports more
 * than one locale object then the supported locale objects are kept in the list
 * 'supportedLocales'.<br>
 * Note: The supported locale objects for a real application are mandatory as the default locale.
 */
@Entity
@Table(name = "bundle_applications")
@NamedQueries({
		@NamedQuery(name = BundleApplications.NQ_FIND_BY_BUNDLE_NAME, query = "select ba from BundleApplications ba, BundleNames bn where :bundleName member of ba.bundleNames") })
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BundleApplications extends ExtraLargeUNameBaseEntity<Integer> implements Cloneable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant for the name of the query find by bundle name . */
	public static final String NQ_FIND_BY_BUNDLE_NAME = "BundleApplications." + "findByBundleName";

	/** The Constant BASE_BUNDLE_APPLICATION is the base name of the initial bundle application. */
	public static final String BASE_BUNDLE_APPLICATION = "base-bundle-application";

	/**
	 * The default locale of this bundle application.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "default_locale_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_BUNDLE_APPLICATIONS_DEFAULT_LOCALE_ID"))
	private LanguageLocales defaultLocale;

	/**
	 * The supported locale objects that are mandatory for this bundle application.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "bundle_application_language_locales", joinColumns = {
			@JoinColumn(name = "application_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "language_locales_id", referencedColumnName = "id") })
	private Set<LanguageLocales> supportedLocales = new HashSet<>();

	/**
	 * Instantiates a new {@link BundleApplications} entity object.
	 *
	 * @param name
	 *            the name
	 * @param bundleNames
	 *            the bundle names
	 * @param defaultLocale
	 *            the default locale
	 */
	@Builder
	BundleApplications(final String name, final LanguageLocales defaultLocale,
		final Set<LanguageLocales> supportedLocales)
	{
		super(name);
		this.defaultLocale = defaultLocale;
		this.supportedLocales = supportedLocales;
	}

}
