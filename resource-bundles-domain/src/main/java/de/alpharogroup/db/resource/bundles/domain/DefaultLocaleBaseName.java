package de.alpharogroup.db.resource.bundles.domain;

import de.alpharogroup.domain.VersionableBaseDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link DefaultLocaleBaseName}.
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefaultLocaleBaseName extends VersionableBaseDomainObject<Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The bundle name. */
	private BundleName bundleName;
	
	/** The default locale. */
	private LanguageLocale defaultLocale;
}
