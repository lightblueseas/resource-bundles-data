package de.alpharogroup.db.resource.bundles.service.util;

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
	public static String forResourcebundles(String baseName, String locale, String key)
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
	public static String forResourcebundles(String baseName, String locale, String key,
		String value)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select rb from Resourcebundles rb");
		boolean baseNameIsNotNull = baseName != null && !baseName.isEmpty();
		if (baseNameIsNotNull)
		{
			sb.append(" ");
			sb.append("where rb.baseName=:baseName");
		}
		boolean localeIsNotNull = locale != null && !locale.isEmpty();
		if (localeIsNotNull)
		{
			sb.append(" ");
			if (baseNameIsNotNull)
			{
				sb.append("and rb.locale=:locale");
			}
			else
			{
				sb.append("where rb.locale=:locale");
			}
		}
		boolean keyIsNotNull = key != null && !key.isEmpty();
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
		boolean valueIsNotNull = value != null && !value.isEmpty();
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
