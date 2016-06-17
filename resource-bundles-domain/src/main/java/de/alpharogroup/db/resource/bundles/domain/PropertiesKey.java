package de.alpharogroup.db.resource.bundles.domain;

import de.alpharogroup.domain.BaseDomainObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link PropertiesKey}.
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class PropertiesKey extends BaseDomainObject<Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private String name;
}
