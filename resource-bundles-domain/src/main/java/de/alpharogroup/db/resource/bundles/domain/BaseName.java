package de.alpharogroup.db.resource.bundles.domain;

import de.alpharogroup.domain.NameBaseDomainObject;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link BaseName}.
 */
@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Builder
public class BaseName extends NameBaseDomainObject<Integer> {
	
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
}
