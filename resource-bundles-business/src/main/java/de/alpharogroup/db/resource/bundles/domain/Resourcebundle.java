package de.alpharogroup.db.resource.bundles.domain;

import de.alpharogroup.db.domain.BaseBusinessObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class Resourcebundle extends BaseBusinessObject<Integer> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The bundle name. */
	private String baseName;
	/** The locale of this entry. */
	private String locale;	
	/** The properties key. */
	private String key;	
	/** The value for the properties key. */
	private String value;
}
