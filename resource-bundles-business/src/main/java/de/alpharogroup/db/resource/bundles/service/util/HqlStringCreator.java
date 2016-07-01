package de.alpharogroup.db.resource.bundles.service.util;

import de.alpharogroup.db.resource.bundles.entities.BaseNames;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.entities.DefaultLocaleBaseNames;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;

/**
 * The class {@link HqlStringCreator} creates hql queries as string objects.
 */
public class HqlStringCreator
{

	/**
	 * Creates hql query for resourcebundles.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @return the string
	 */
	public static String forResourcebundles(final String baseName, final String locale, final String key)
	{
		return forResourcebundles(baseName, locale, key, null);
	}

	/**
	 * Creates hql query for resourcebundles.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * @param key the key
	 * @param value the value
	 * @return the string
	 */
	public static String forResourcebundles(final String baseName, final String locale, final String key,
		final String value)
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
	 * Creates hql query for {@link BaseNames}.
	 *
	 * @param baseName the base name
	 * 
	 * @return the hql string
	 */
	public static String forBaseNames(final String baseName) {
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
	 * Creates hql query for {@link BundleNames}.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * 
	 * @return the string
	 */
	public static String forBundleNames(final String baseName, final String locale) {
		final StringBuilder sb = new StringBuilder();
		sb.append("select bn from " + BundleNames.class.getSimpleName() + " bn");
		final boolean baseNameIsNotNull = baseName != null && !baseName.isEmpty();
		if (baseNameIsNotNull)
		{
			sb.append(" ");
			sb.append("where bn.baseName.name=:baseName");
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
	 * Creates hql query for {@link DefaultLocaleBaseNames}.
	 *
	 * @param baseName the base name
	 * @param locale the locale
	 * 
	 * @return the string
	 */
	public static String forDefaultLocaleBaseNames(final String baseName, final String locale) {
		final StringBuilder sb = new StringBuilder();
		sb.append("select bn from " + DefaultLocaleBaseNames.class.getSimpleName() + " bn");
		final boolean baseNameIsNotNull = baseName != null && !baseName.isEmpty();
		if (baseNameIsNotNull)
		{
			sb.append(" ");
			sb.append("where bn.bundleName.baseName.name=:baseName");
		}
		final boolean localeIsNotNull = locale != null && !locale.isEmpty();
		if (localeIsNotNull)
		{
			sb.append(" ");
			if (baseNameIsNotNull)
			{
				sb.append("and bn.defaultLocale.locale=:locale");
			}
			else
			{
				sb.append("where bn.defaultLocale.locale=:locale");
			}
		}
		return sb.toString();		
	}



	/**
	 * Creates hql query for {@link LanguageLocales}.
	 *
	 * @param baseName the base name
	 * 
	 * @return the hql string
	 */
	public static String forLanguageLocales(final String locale) {
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
	 * Creates hql query for {@link PropertiesKeys}.
	 *
	 * @param baseName the base name
	 * 
	 * @return the hql string
	 */
	public static String forPropertiesKeys(final String propertiesKey) {
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

}
