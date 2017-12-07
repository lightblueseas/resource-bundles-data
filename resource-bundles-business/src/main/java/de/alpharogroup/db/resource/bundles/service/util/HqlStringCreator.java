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
package de.alpharogroup.db.resource.bundles.service.util;

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;

/**
 * The class {@link HqlStringCreator} creates hql queries as string objects.
 */
public class HqlStringCreator
{

	/**
	 * Creates hql query for {@link BaseNames}.
	 *
	 * @param baseName
	 *            the base name
	 *
	 * @return the hql string
	 */
	public static String forBaseNames(final String baseName)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select bn from " + BaseNames.class.getSimpleName() + " bn");
		final boolean baseNameIsNotNull = baseName != null && !baseName.isEmpty();
		if (baseNameIsNotNull)
		{
			sb.append(" ");
			sb.append("where bn.name=:baseName");
		}
		return sb.toString();
	}

	/**
	 * Creates hql query for {@link BundleApplications}.
	 *
	 * @param name
	 *            the name
	 *
	 * @return the hql string
	 */
	public static String forBundleApplications(final String name)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select bn from " + BundleApplications.class.getSimpleName() + " bn");
		final boolean baseNameIsNotNull = name != null && !name.isEmpty();
		if (baseNameIsNotNull)
		{
			sb.append(" ");
			sb.append("where bn.name=:name");
		}
		return sb.toString();
	}

	/**
	 * Creates hql query for {@link BundleNames}.
	 *
	 * @param owner
	 *            the owner
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @return the string
	 */
	public static String forBundleNames(final String owner, final String baseName,
		final String locale)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select bn from " + BundleNames.class.getSimpleName() + " bn");
		final boolean ownerIsNotNull = owner != null && !owner.isEmpty();
		if (ownerIsNotNull)
		{
			sb.append(" ");
			sb.append("where bn.owner=:owner");
		}
		final boolean baseNameIsNotNull = baseName != null && !baseName.isEmpty();
		if (baseNameIsNotNull)
		{
			sb.append(" ");
			if (ownerIsNotNull)
			{
				sb.append("and bn.baseName.name=:baseName");
			}
			else
			{
				sb.append("where bn.baseName.name=:baseName");
			}
		}
		final boolean localeIsNotNull = locale != null && !locale.isEmpty();
		if (localeIsNotNull)
		{
			sb.append(" ");
			if (baseNameIsNotNull)
			{
				sb.append("and bn.locale.locale=:locale");
			}
			else
			{
				sb.append("where bn.locale.locale=:locale");
			}
		}
		return sb.toString();
	}

	/**
	 * Creates hql query for {@link LanguageLocales}.
	 *
	 * @param locale
	 *            the locale
	 * @param locale
	 *            the locale
	 *
	 * @return the hql string
	 */
	public static String forLanguageLocales(final String locale)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select bn from " + LanguageLocales.class.getSimpleName() + " bn");
		final boolean localeIsNotNull = locale != null && !locale.isEmpty();
		if (localeIsNotNull)
		{
			sb.append(" ");
			sb.append("where bn.locale=:locale");
		}
		return sb.toString();
	}

	/**
	 * Creates hql query for {@link Languages}.
	 *
	 * @param name
	 *            the name
	 * @param iso639Dash1
	 *            the iso639Dash1
	 *
	 * @return the hql string
	 */
	public static String forLanguages(final String name, final String iso639Dash1)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select bn from " + Languages.class.getSimpleName() + " bn");
		final boolean nameIsNotNull = name != null && !name.isEmpty();
		if (nameIsNotNull)
		{
			sb.append(" ");
			sb.append("where bn.name=:name");
		}
		final boolean iso639Dash1IsNotNull = iso639Dash1 != null && !iso639Dash1.isEmpty();
		if (iso639Dash1IsNotNull)
		{
			sb.append(" ");
			if (nameIsNotNull)
			{
				sb.append("and bn.iso639Dash1=:iso639Dash1");
			}
			else
			{
				sb.append("where bn.iso639Dash1=:iso639Dash1");
			}
		}
		return sb.toString();
	}

	/**
	 * Creates hql query for {@link PropertiesKeys}.
	 *
	 * @param propertiesKey
	 *            the properties key
	 * @return the hql string
	 */
	public static String forPropertiesKeys(final String propertiesKey)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select bn from " + PropertiesKeys.class.getSimpleName() + " bn");
		final boolean propertiesKeyIsNotNull = propertiesKey != null && !propertiesKey.isEmpty();
		if (propertiesKeyIsNotNull)
		{
			sb.append(" ");
			sb.append("where bn.name=:propertiesKey");
		}
		return sb.toString();
	}

	/**
	 * Creates hql query for resourcebundles.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String forResourcebundles(final String baseName, final String locale,
		final String key)
	{
		return forResourcebundles(baseName, locale, key, null);
	}

	/**
	 * Creates hql query for resourcebundles.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String forResourcebundles(final String baseName, final String locale,
		final String key, final String value)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select rb from " + Resourcebundles.class.getSimpleName() + " rb");
		final boolean baseNameIsNotNull = baseName != null && !baseName.isEmpty();
		if (baseNameIsNotNull)
		{
			sb.append(" ");
			sb.append("where rb.bundleName.baseName.name=:baseName");
		}
		final boolean localeIsNotNull = locale != null && !locale.isEmpty();
		if (localeIsNotNull)
		{
			sb.append(" ");
			if (baseNameIsNotNull)
			{
				sb.append("and rb.bundleName.locale.locale=:locale");
			}
			else
			{
				sb.append("where rb.bundleName.locale.locale=:locale");
			}
		}
		final boolean keyIsNotNull = key != null && !key.isEmpty();
		if (keyIsNotNull)
		{
			sb.append(" ");
			if (!baseNameIsNotNull && !localeIsNotNull)
			{
				sb.append("where rb.key.name=:key");
			}
			else
			{
				sb.append("and rb.key.name=:key");
			}
		}
		final boolean valueIsNotNull = value != null && !value.isEmpty();
		if (valueIsNotNull)
		{
			sb.append(" ");
			if (!baseNameIsNotNull && !localeIsNotNull && !keyIsNotNull)
			{
				sb.append("where rb.value=:value");
			}
			else
			{
				sb.append("and rb.value=:value");
			}
		}
		return sb.toString();
	}

	/**
	 * Creates hql query for resourcebundles.
	 *
	 * @param baseName
	 *            the base name
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String forResourcebundles(final String owner, final String baseName, final String locale,
		final String key, final String value)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select rb from " + Resourcebundles.class.getSimpleName() + " rb");

		final boolean ownerIsNotNull = owner != null && !owner.isEmpty();
		if (ownerIsNotNull)
		{
			sb.append(" ");
			sb.append("where rb.bundleName.owner=:owner");
		}

		final boolean baseNameIsNotNull = baseName != null && !baseName.isEmpty();
		if (baseNameIsNotNull)
		{
			sb.append(" ");
			if(ownerIsNotNull) {
				sb.append("and rb.bundleName.baseName.name=:baseName");
			} else {
				sb.append("where rb.bundleName.baseName.name=:baseName");
			}
		}

		final boolean localeIsNotNull = locale != null && !locale.isEmpty();
		if (localeIsNotNull)
		{
			sb.append(" ");
			if (baseNameIsNotNull)
			{
				sb.append("and rb.bundleName.locale.locale=:locale");
			}
			else
			{
				sb.append("where rb.bundleName.locale.locale=:locale");
			}
		}

		final boolean keyIsNotNull = key != null && !key.isEmpty();
		if (keyIsNotNull)
		{
			sb.append(" ");
			if (!baseNameIsNotNull && !localeIsNotNull)
			{
				sb.append("where rb.key.name=:key");
			}
			else
			{
				sb.append("and rb.key.name=:key");
			}
		}

		final boolean valueIsNotNull = value != null && !value.isEmpty();
		if (valueIsNotNull)
		{
			sb.append(" ");
			if (!baseNameIsNotNull && !localeIsNotNull && !keyIsNotNull)
			{
				sb.append("where rb.value=:value");
			}
			else
			{
				sb.append("and rb.value=:value");
			}
		}
		return sb.toString();
	}

}
