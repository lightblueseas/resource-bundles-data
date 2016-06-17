package de.alpharogroup.db.resource.bundles.domain;

import de.alpharogroup.domain.BaseDomainObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link DefaultLocaleBaseName}.
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class DefaultLocaleBaseName extends BaseDomainObject<Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The bundle name. */
	private BundleName bundleName;
	private LanguageLocale defaultLocale;
}
