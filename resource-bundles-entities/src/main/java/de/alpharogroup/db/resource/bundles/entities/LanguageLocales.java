package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.VersionableBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * Entity class for saving in database the locale of {@link BundleNames} objects.
 */
@Entity
@Table(name="language_locales")
@Getter
@Setter
@NoArgsConstructor
public class LanguageLocales 
extends VersionableBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;
	/** The locale of this entry. */
	@Column( length = 64  )
	private String locale;
	
}