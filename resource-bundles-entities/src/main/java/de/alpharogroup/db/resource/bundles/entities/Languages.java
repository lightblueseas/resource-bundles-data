package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.ExtraSmallNameBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * Entity class for saving in database languages.
 */
@Entity
@Table(name="languages")
@Getter
@Setter
@NoArgsConstructor
public class Languages 
extends ExtraSmallNameBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	/** The iso639_1 code with two characters. */
	@Column(name = "iso639_1", length = 2)
	private String iso639Dash1;	
	
}
