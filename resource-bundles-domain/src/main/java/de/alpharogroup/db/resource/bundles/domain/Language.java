package de.alpharogroup.db.resource.bundles.domain;

import de.alpharogroup.domain.NameBaseDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link Language}.
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Language extends NameBaseDomainObject<Integer> {
	
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The iso639_1 code with two characters. */
	private String iso639Dash1;	
	
}
