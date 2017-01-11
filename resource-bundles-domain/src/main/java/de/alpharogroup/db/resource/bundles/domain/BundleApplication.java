package de.alpharogroup.db.resource.bundles.domain;

import java.util.HashSet;
import java.util.Set;

import de.alpharogroup.domain.NameBaseDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link BundleApplication}.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BundleApplication extends NameBaseDomainObject<Integer> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	
	/** The bundle names of this application. */
	private Set<BundleName> bundleNames = new HashSet<>();

}
