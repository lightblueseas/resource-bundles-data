package de.alpharogroup.db.resource.bundles.service.util;

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
			sb.append("where rb.bundleName.baseName=:baseName");
		}
		final boolean localeIsNotNull = locale != null && !locale.isEmpty();
		if (localeIsNotNull)
		{
			sb.append(" ");
			if (baseNameIsNotNull)
			{
				sb.append("and rb.bundleName.locale=:locale");
			}
			else
			{
				sb.append("where rb.bundleName.locale=:locale");
			}
		}
		final boolean keyIsNotNull = key != null && !key.isEmpty();
		if (keyIsNotNull)
		{
			sb.append(" ");
			if (!baseNameIsNotNull && !localeIsNotNull)
			{
				sb.append("where rb.key=:key");
			}
			else
			{
				sb.append("and rb.key=:key");
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
